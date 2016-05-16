package org.hu.web.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hu.data.dao.AnnocationUserDao;
import org.hu.data.model.User;
import org.hu.export.UserExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Controller
public class UserController {
	@Autowired
	AnnocationUserDao auserdao;
	
	@RequestMapping(value="/alluser", method=RequestMethod.GET)
	public String getAllUser(@RequestParam(value="pagesize",defaultValue="10") int pagesize,						
							@RequestParam(value="currentpage",defaultValue="1") int currentpage,
							@RequestParam(value="qry_id",required=false,defaultValue="") String qry_id,
							@RequestParam(value="qry_username",required=false,defaultValue="") String qry_username,
							Model model){	
		//sb用于拼接查询条件 
		StringBuilder sb = new StringBuilder();		
		if(!(qry_id.equals("") && qry_username.equals(""))){
			sb.append("where ");
			if(!qry_id.equals("")){
				sb.append("id='"+qry_id+"'");
				if(!qry_username.equals("")){
					sb.append(" and username='"+qry_username+"'");
				}
			}else{
				sb.append("username='"+qry_username+"'");

			}	
		}	
		int start = (currentpage-1) * pagesize;
		Map map = new HashMap();
		map.put("start", start);
		map.put("count", pagesize);
		map.put("sb",sb);
		int pagetotal = auserdao.getCount(map).size();
		List<User> list = auserdao.getUsers(map);
		model.addAttribute("qry_id", qry_id);
		model.addAttribute("qry_username", qry_username);
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
		User user = new User(id, username, password);
		if(auserdao.get(user.getId()) != null){
			model.addFlashAttribute("message","此id已经存在,请重新输入");
			return "redirect:/alluser";		
		}
		else{
			auserdao.add(user);
		}
		return "redirect:/alluser";
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.GET)
	public String updateUser(@RequestParam("id") int id, Model model){
		User user = auserdao.get(id);
		model.addAttribute("user", user);
		return "updateuser";
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.POST)
	public String update(@RequestParam("id") int id, 
						@RequestParam("username") String username, 
						@RequestParam("password") String password){
		User user = new User(id, username, password);
		auserdao.update(user);
		return "redirect:/alluser";
	}
	
	@RequestMapping(value="/export")
	public ModelAndView export(@RequestParam(value="qry_id",required=false,defaultValue="") String qry_id,
								@RequestParam(value="qry_username",required=false,defaultValue="") String qry_username,
								Map<String, Object> model) throws ParseException{
		StringBuilder sb = new StringBuilder();		
		if(!(qry_id.equals("") && qry_username.equals(""))){
			sb.append("where ");
			if(!qry_id.equals("")){
				sb.append("id='"+qry_id+"'");
				if(!qry_username.equals("")){
					sb.append(" and username='"+qry_username+"'");
				}
			}else{
				sb.append("username='"+qry_username+"'");

			}	
		}
		Map map = new HashMap();
		map.put("sb", sb);
		List<User> userList = auserdao.getCount(map);
		UserExcelView userExcelView = new UserExcelView();
		model.put("userList", userList);
		return new ModelAndView(userExcelView, model);
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
