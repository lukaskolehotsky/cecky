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

        msg.setSubject("Inzerát vytvorený - " + brand + " " + type);
        msg.setText("Dobrý deň, \n autentifikačný kód pre odstránenie alebo úpravu Vášho inzerátu je - " + authenticationCode);

        javaMailSender.send(msg);
    }
	
	public void sendEmailToOwner(String ownerEmail, ContactOwnerRequest request) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("lukaskolehotsky@gmail.com");
        msg.setTo(ownerEmail);

        msg.setSubject("Gratulujeme, našiel sa záujemca o Váš inzerát.");
        msg.setText("Záujemca Vám nechal odkaz: \n \n" + request.getBuyerMessage() + "\n Môžete ho kontaktovať emailom - " + request.getBuyerEmail() + "\n alebo telefonicky - " + request.getBuyerMobile());

        javaMailSender.send(msg);
	}

}
