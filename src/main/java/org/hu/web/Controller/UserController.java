package org.hu.web.Controller;

import java.util.List;

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
	public String getAllUser(Model model){
		List<User> list = auserdao.getAll();
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
			model.addFlashAttribute("message","��id�Ѿ�����,����������");
			return "redirect:/alluser";		
		}
		else{
			auserdao.add(user);
		}
		return "redirect:/alluser";
	}
	
	/*
	 * ��ȡÿһ�����������ķ���
	 */
//	@RequestMapping(value="/ajax")
//	public @ResponseBody User ajaxdemo(@RequestParam(value="id") String id){
//		User user = new User(55,"55","56");
//		System.out.println(id);
//		return user;
//	}
	
	@RequestMapping(value="/ajaxAddUser")
	public String ajaxAddUser(@RequestBody User user){
		auserdao.add(user);
		return "redirect:/alluser";
	}
	
	/*
	 * ʹ��@RequestBody��@ResponseBody���Ӷ��User�ķ���
	 */
//	@RequestMapping(value="/ajaxAddUsers")
//	public @ResponseBody User ajaxAddUser(@RequestBody User user){
//		//System.out.println(user.getId());
//		return user;
//	}
}
