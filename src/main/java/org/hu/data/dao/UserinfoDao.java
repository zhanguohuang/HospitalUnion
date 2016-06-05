package org.hu.data.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hu.data.model.Userinfo;

public interface UserinfoDao {
	@Insert("insert into userinfo(username,image_url) values(#{username},#{image_url})")
	public void add(Userinfo userinfo);
	
	@Select("select * from userinfo where username=#{username})")
	public Userinfo get(String username);
	
	@Update("update userinfo set image_url=#{image_url} where username=#{username}")
	public void update(Userinfo userinfo);
}
