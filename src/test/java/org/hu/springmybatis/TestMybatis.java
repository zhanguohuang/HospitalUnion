package org.hu.springmybatis;

import org.hu.config.RootConfig;
import org.hu.data.dao.UserDao;
import org.hu.data.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMybatis {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(RootConfig.class);
		UserDao userDao = (UserDao)ctx.getBean("userDao");
		
		User user = new User();
		//-----添加数据-----//
		user.setId(2);
		user.setUsername("hello");
		user.setPassword("world");
		userDao.addUser(user);
		System.out.println("添加成功");
		//-----查询数据-----//
		user.setUsername("mysql");
		user.setPassword("123");
		System.out.println(userDao.getUser(user).toString());
		user.setUsername("hello");
		user.setPassword("world");
		System.out.println(userDao.getUser(user).toString());
		//-----修改数据-----//
		user.setId(2);
		user.setPassword("802");
		userDao.updateUser(user);
		System.out.println("修改成功");
		//-----删除数据-----//
		userDao.deleteUser(1);
		System.out.println("删除成功");
	}

}
