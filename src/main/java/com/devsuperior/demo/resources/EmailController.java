package com.devsuperior.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.demo.services.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<Void> sendEmail(@Valid @RequestBody String to, String subject, String body) {
		emailService.sendEmail(to, subject, body);
		return ResponseEntity.noContent().build();
	}
}
