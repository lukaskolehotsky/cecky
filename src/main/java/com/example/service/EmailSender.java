package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String authenticationCode, String email, String brand, String type) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("lukaskolehotsky@gmail.com");
        msg.setTo(email);

        msg.setSubject("Item Created");
        msg.setText("Hello, \n Your authentication code for modification " + brand + " " + type + " has been created - " + authenticationCode);

        javaMailSender.send(msg);
    }

}
