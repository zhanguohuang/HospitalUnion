package org.hu.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
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
	
	@Select("select * from user limit #{start},#{count}")
	public List<User> getUsers(Map<String,Integer> map);
	
	@Select("select * from user")
	public List<User> getCount();
	
//	@Select("select count(*) from user")
//	public List getCount();
}
