package org.hu.web.controller;

import org.hu.data.dao.AnnocationUserDao;
import org.hu.data.model.User;
import org.hu.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
	@Autowired
	AnnocationUserDao auserdao;
	
	@Autowired
	SendEmailService sendEmailService;
	
	@RequestMapping("/email")
	public String preparedSendEmail(@RequestParam(value="id",required=true) int id,Model model){
		User user = auserdao.get(id);
		model.addAttribute("user", user);
		return "email_input";
	}
	
	@RequestMapping("/sendemail")
	public String sendEmail(@RequestParam(value="username",required=false,defaultValue="") String username,
							@RequestParam(value="email",required=true) String email,
							@RequestParam(value="content",required=true) String content,
							Model model){
		content = "Hello, " + username +":\r\n	" + content;
		String message = "邮件发送成功！";
		try{
			sendEmailService.send(email, content);
		}catch(Exception e){
			message = "错误,邮件发送失败！请确认邮箱是否存在且正确";
		}finally{
			model.addAttribute("message", message);
		}
		return "email_ok";
	}
}
