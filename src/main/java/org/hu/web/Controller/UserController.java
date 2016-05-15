package org.hu.web.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hu.data.dao.AnnocationUserDao;
import org.hu.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
	@Autowired
	AnnocationUserDao auserdao;
	
	@RequestMapping(value="/alluser", method=RequestMethod.GET)
	public String getAllUser(@RequestParam(value="pagesize",defaultValue="10") int pagesize,						
							@RequestParam(value="currentpage",defaultValue="1") int currentpage,
							Model model){	
		int pagetotal = auserdao.getCount().size();
		int start = (currentpage-1) * pagesize;
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("count", pagesize);
		List<User> list = auserdao.getUsers(map);
		model.addAttribute("pagetotal", pagetotal);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("userList", list);
		return "alluser";
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public String addUser(@RequestParam("id") int id,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			RedirectAttributes model){
		System.out.println("id=" + id + ", username=" + username +", password=" + password +";");
		User user = new User(id, username, password);
		if(auserdao.get(user) != null){
			model.addFlashAttribute("message","此id已经存在,请重新输入");
			return "redirect:/alluser";		
		}
		else{
			int i = auserdao.add(user);
		}
		return "redirect:/alluser";
	}
	
	/*
	 * 获取每一个独立参数的方法
	 */
//	@RequestMapping(value="/ajax")
//	public @ResponseBody User ajaxdemo(@RequestParam(value="id") String id){
//		User user = new User(55,"55","56");
//		System.out.println(id);
//		return user;
//	}
	
	/*
	 * 增加一个User,然后重定向到/alluser
	 */
	@RequestMapping(value="/ajaxAddUser")
	public String ajaxAddUser(@RequestBody User user){
		auserdao.add(user);
		return "redirect:/alluser";
	}
	
	/*
	 * 使用@RequestBody和@ResponseBody增加多个User的方法
	 */
//	@RequestMapping(value="/ajaxAddUsers")
//	public @ResponseBody User ajaxAddUser(@RequestBody User user){
//		//System.out.println(user.getId());
//		return user;
//	}
}
