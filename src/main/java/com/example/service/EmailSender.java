package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.requests.ContactOwnerRequest;

@Service
public class EmailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String authenticationCode, String email, String brand, String type) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("lukaskolehotsky@gmail.com");
        msg.setTo(email);

        msg.setSubject("Inzerat vytvoreny - " + brand + " " + type);
        msg.setText("Dobry den, \n autentifikacny kod pre odstranenie alebo upravu Vasho inzeratu je - " + authenticationCode);

        javaMailSender.send(msg);
    }
	
	public void sendEmailToOwner(String ownerEmail, ContactOwnerRequest request) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("lukaskolehotsky@gmail.com");
        msg.setTo(ownerEmail);

        msg.setSubject("Gratulujeme, nasiel sa zaujemca o Vas inzerat.");
        msg.setText("Zaujemca Vam nechal odkaz: \n \n" + request.getBuyerMessage() + "\n Mozete ho kontaktovat emailom - " + request.getBuyerEmail() + "\n alebo telefonicky - " + request.getBuyerMobile());

        javaMailSender.send(msg);
	}

}
