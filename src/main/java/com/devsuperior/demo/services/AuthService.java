package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EmailDTO;
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

        String test = "é o tal do disparo do lvs filho da puta, sai da frente XDXDXDDDD";
        emailService.sendEmail(body.getEmail(), "Recuperação de senha", test);

    }


}
