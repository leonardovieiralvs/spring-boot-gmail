package com.devsuperior.demo.resources;

import com.devsuperior.demo.dto.EmailDTO;
import com.devsuperior.demo.dto.NewPasswordDTO;
import com.devsuperior.demo.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/recover-token")
    private ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO body) {
        authService.createRecoverToken(body);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/new-password")
    private ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDTO body) {
        authService.saveNewPassword(body);
        return ResponseEntity.ok().build();
    }
}
