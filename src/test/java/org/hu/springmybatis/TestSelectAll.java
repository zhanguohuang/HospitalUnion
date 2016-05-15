package org.hu.springmybatis;

import java.util.List;

import org.hu.config.RootConfig;
import org.hu.data.dao.AnnocationUserDao;
import org.hu.data.dao.UserDao;
import org.hu.data.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSelectAll {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(RootConfig.class);
		AnnocationUserDao auserdao = (AnnocationUserDao) ctx.getBean("annocationUserDao");
		//List<User> list = auserdao.getCount(map);
//		for(User user : list){
//			System.out.println(user);
//		}
	}

}
