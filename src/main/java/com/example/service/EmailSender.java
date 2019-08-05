package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

		@Autowired
	    private JavaMailSender javaMailSender;
		
		void sendEmail() {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setFrom("lukaskolehotsky@gmail.com");
	        msg.setTo("lukaskolehotsky@gmail.com");

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Hello World \n Spring Boot Email");

	        javaMailSender.send(msg);

	    }

}
