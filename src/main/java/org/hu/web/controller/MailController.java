package org.hu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {
	@Autowired
	JavaMailSender mailSender;
	
	@RequestMapping("/mail")
	public String sendMail(){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("zhanguohuang@szhuarong.com");
		message.setTo("779134714@qq.com");
		message.setSubject("hello");
		message.setText("I received the message");
		mailSender.send(message);
		return "redirect:/";
	}
}
