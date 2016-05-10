package org.hu.web.Controller;

import java.util.List;

import org.hu.data.dao.AnnocationUserDao;
import org.hu.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
