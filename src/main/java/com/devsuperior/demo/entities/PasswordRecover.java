package com.devsuperior.demo.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_password_recover")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }
}
