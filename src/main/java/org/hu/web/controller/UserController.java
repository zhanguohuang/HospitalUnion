package org.hu.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hu.annocation.SystemControllerLog;
import org.hu.data.dao.AnnocationUserDao;
import org.hu.data.dao.ChatinfoDao;
import org.hu.data.dao.UserinfoDao;
import org.hu.data.model.User;
import org.hu.data.model.Userinfo;
import org.hu.export.UserExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.management.VMOption.Origin;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Controller
public class UserController {
	@Autowired
	AnnocationUserDao auserdao;
	
	@Autowired
	UserinfoDao userinfodao;
	
	@Autowired
	ChatinfoDao chatinfodao;
	
	@RequestMapping(value="/alluser", method=RequestMethod.GET)
	//@SystemControllerLog(description = "列出所有的user")
	public String getAllUser(@RequestParam(value="pagesize",defaultValue="10") int pagesize,						
							@RequestParam(value="currentpage",defaultValue="1") int currentpage,
							@RequestParam(value="qry_id",required=false,defaultValue="") String qry_id,
							@RequestParam(value="qry_username",required=false,defaultValue="") String qry_username,
							@RequestParam(value="qry_email",required=false,defaultValue="") String qry_email,
							Model model){	
		//sb用于拼接查询条件 
		StringBuilder sb = new StringBuilder();		
		if(!qry_id.equals("")){
			sb.append(" and id like '%"+qry_id+"%'");
		}
		if(!qry_username.equals("")){
				sb.append(" and username like '%"+qry_username+"%'");
		}
		if(!qry_email.equals("")){
				sb.append(" and email like '%"+qry_email+"%'");
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
		model.addAttribute("qry_email", qry_email);
		model.addAttribute("pagetotal", pagetotal);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("userList", list);
		return "alluser";
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public String addUser(@RequestParam("id") int id,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="email",required=false,defaultValue="") String email,
			RedirectAttributes model){
		User user = new User(id, username, password,email);
		if(auserdao.get(user.getId()) != null){
			model.addFlashAttribute("message","此id已经存在,请重新输入");
			return "redirect:/alluser";		
		}
		else{
			auserdao.add(user);
			chatinfodao.adduserinfo(username);
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
						@RequestParam("password") String password,
						@RequestParam(value="email",required=false,defaultValue="") String email,
						@RequestPart(value="userimage",required=false) MultipartFile userimage,
						HttpServletRequest request) throws Exception{
		if(!userimage.getOriginalFilename().equals("")){
			//本地Eclipse版的Imagepath是被写死的
			//String imagepath = "C:\\development\\workspace\\HospitalUnion\\src\\main\\webapp\\image\\";
			//Tomcat版
			String imagepath = request.getSession().getServletContext().getRealPath("/")+"image\\";
			String originName = userimage.getOriginalFilename();
			String fileNameSuffix = originName.substring(originName.indexOf("."),originName.length());
			//文件的名称保存方式为 id_username_image.jepg如1_zhanguohuang_image.jpg
			userimage.transferTo(new File(imagepath + id + "_" + username + "_image" + fileNameSuffix));
			//image_url的保存方式为 image/id_username_image.jepg 用于获取此文件
			String image_url = "image/" + id + "_" + username + "_image" + fileNameSuffix;
			Userinfo userinfo = new Userinfo();
			userinfo.setUsername(username);
			userinfo.setImage_url(image_url);
			userinfodao.update(userinfo);
		}		
		User user = new User(id, username, password, email);
		auserdao.update(user);
		return "redirect:/alluser";
	}
	
	/**
	 * 根据id删除一个用户User。<br>
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteuser", method=RequestMethod.GET)
	public String delete( @RequestParam(value="id",required=true) int id){
		auserdao.delete(id);
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
		chatinfodao.adduserinfo(user.getUsername());
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
