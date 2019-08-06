package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

		@Autowired
	    private JavaMailSender javaMailSender;
		
		void sendEmail(String authenticationCode, String email) {
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setFrom("lukaskolehotsky@gmail.com");
	        msg.setTo(email);

	        msg.setSubject("Item Created");
	        msg.setText("Hello, \n Your authentication key for modification has been created - " + authenticationCode);

	        javaMailSender.send(msg);
	    }

	    public void authenticateEmail(String email, String key){

		}

}
