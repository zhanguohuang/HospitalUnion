package org.hu.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hu.data.dao.ChatinfoDao;
import org.hu.data.model.Chatinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
	@Autowired
	ChatinfoDao chatinfodao;
	
	@RequestMapping("/chatroom")
	public String enterChatroom(HttpServletRequest request,
								Model model){
		//获取登录用户的用户名字
		String username = request.getUserPrincipal().getName();
		List<Chatinfo> list = chatinfodao.getAll();
		model.addAttribute("username", username);
		model.addAttribute("chatinfolist", list);
		return "chatroom";
	}
	
	@RequestMapping(value="/addchatinfo")
	public void addchatinfo(@RequestParam(value="username") String username,
							@RequestParam(value="message") String message){
		Chatinfo chatinfo = new Chatinfo();
		chatinfo.setUsername(username);
		chatinfo.setMessage(message);
		chatinfodao.add(chatinfo);
	}
}
