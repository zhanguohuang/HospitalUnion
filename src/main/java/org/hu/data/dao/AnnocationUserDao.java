package org.hu.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.hu.annocation.SystemControllerLog;
import org.hu.data.model.User;

public interface AnnocationUserDao {
	@Insert("insert into user(id, username, password,email) values(#{id}, #{username}, #{password},#{email})")
	public int add(User user);
	
	@Select("select * from user where id=#{id}")
	public User get(int id);
	
	@Update("update user set username=#{username}, password=#{password},email=#{email} where id=#{id}")
	public void update(User user);
	
	@Delete("delete from user where id=#{id}")
	public void delete(int id);
	
	@Select("select * from user where 1=1 ${sb} limit #{start},#{count}")
	public List<User> getUsers(Map map);

	@Select("select * from user where 1=1 ${sb}")
	public List<User> getCount(Map map);
	
//	@Select("select count(*) from user")
//	public List getCount();
}
