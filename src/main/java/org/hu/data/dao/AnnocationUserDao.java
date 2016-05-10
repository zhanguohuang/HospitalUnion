package org.hu.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hu.data.model.User;

public interface AnnocationUserDao {
	@Insert("insert into user(id, username, password) values(#{id}, #{username}, #{password})")
	public int add(User user);
	
	@Select("select * from user where id=#{id}")
	public User get(User user);
	
	@Update("update user set username=#{username}, password=#{password} where id=#{id})")
	public User update(User user);
	
	@Delete("delete * from user where id=#{id}")
	public void delete(int id);
	
	@Select("select * from user")
	public List<User> getAll();
}
