package com.devsuperior.demo.repository;

import com.devsuperior.demo.entities.PasswordRecover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {

    PasswordRecover findByToken(String token);
}
