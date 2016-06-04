package org.hu.data.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.hu.data.model.Userinfo;

public interface UserinfoDao {
	@Insert("insert into userinfo(username,image_url) values(#{username},#{image_url})")
	public void add(Userinfo userinfo);
	
	@Select("select * from userinfo where username=#{username})")
	public Userinfo get(String username);
}
