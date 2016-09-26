package org.hu.serviceImpl;

import org.hu.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService{
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
