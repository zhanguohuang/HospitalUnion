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
		//��ȡ��¼�û����û�����
		String username = request.getUserPrincipal().getName();
		List<Chatinfo> list = chatinfodao.getAll();
		String image_url = chatinfodao.getImage_url(username);
		Collections.reverse(list);
		model.addAttribute("username", username);
		model.addAttribute("chatinfolist", list);
		model.addAttribute("image_url", image_url);
		if(list.size()>0){
			model.addAttribute("lasttime",list.get(0).getCreate_time());
		}else{
			model.addAttribute("lasttime","0000-00-00 00:00:00.0");
		}		
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
