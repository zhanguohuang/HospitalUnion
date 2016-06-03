package org.hu.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hu.data.dao.ChatinfoDao;
import org.hu.data.model.Chatinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		Collections.reverse(list);
		model.addAttribute("username", username);
		model.addAttribute("chatinfolist", list);
		model.addAttribute("lasttime",list.get(0).getCreate_time());
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
	
	@RequestMapping(value="/ajaxaddchatinfo",method=RequestMethod.POST)
	@ResponseBody
	public void addchatinfo(@RequestBody Chatinfo chatinfo){
		chatinfodao.add(chatinfo);
	}
	
	@RequestMapping(value="/getCurrentChatinfo",method=RequestMethod.POST)
	public @ResponseBody List<Chatinfo> 
		getCurrentChatinfo(@RequestBody Chatinfo chatinfo){
		Map map = new HashMap();
		map.put("username", chatinfo.getUsername());
		map.put("lasttime", chatinfo.getCreate_time());
		List<Chatinfo> list = chatinfodao.getCurrentChatinfo(map);
		return list;
	}
	

	
}
