package com.devsuperior.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tb_password_recover")
@Getter
@Setter
public class PasswordRecover {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Instant expiration;

    public PasswordRecover() {
    }

    public PasswordRecover(Long id, String email, String token, Instant expiration) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expiration = expiration;
    }

}
