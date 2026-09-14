package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EmailDTO;
import com.devsuperior.demo.dto.NewPasswordDTO;
import com.devsuperior.demo.entities.PasswordRecover;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.repository.PasswordRecoverRepository;
import com.devsuperior.demo.repository.UserRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    public void createRecoverToken(EmailDTO body) {

        User user = userRepository.findByEmail(body.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(user.getEmail());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60));

        passwordRecoverRepository.save(entity);

        String text = "Acesse o link para definir uma nova senha (válido por " + tokenMinutes + " minutos):\n\n"
                + recoverUri + token;

        emailService.sendEmail(body.getEmail(), "Recuperação de senha", text);

    }

    public void saveNewPassword(NewPasswordDTO body) {
        PasswordRecover passwordRecover = passwordRecoverRepository.findByToken(body.getToken());
        if (passwordRecover == null || passwordRecover.getExpiration().isBefore(Instant.now())) {
            throw new ResourceNotFoundException("Token inválido ou expirado");
        }

        User user = userRepository.findByEmail(passwordRecover.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        user.setPassword(body.getNewPassword());
        userRepository.save(user);
    }
}