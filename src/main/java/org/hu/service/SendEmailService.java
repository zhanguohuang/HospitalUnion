package org.hu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	@Autowired
	JavaMailSenderImpl mailSender;
	
	public void send(String to, String Content){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("zhanguohuang@szhuarong.com");
		message.setTo(to);
		message.setSubject("Hello, The email from HosipitalUnion");
		message.setText(Content);
		mailSender.send(message);
	}
}
