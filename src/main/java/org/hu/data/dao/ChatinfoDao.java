package org.hu.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.hu.data.model.Chatinfo;

public interface ChatinfoDao {
	@Select("select a.id,a.username,a.message,a.create_time,b.image_url from chatinfo a left join userinfo b on a.username=b.username order by create_time desc limit 0,10")
	public List<Chatinfo> getAll();
	
	@Insert("insert into chatinfo(username,message,create_time) values(#{username},#{message},now())")
	public void add(Chatinfo chatinfo);
	
	@Select("select a.id,a.username,a.message,a.create_time,b.image_url from chatinfo a left join userinfo b on a.username=b.username where a.username!=#{username} and create_time>#{lasttime}")
	public List<Chatinfo> getCurrentChatinfo(Map map);
	
	@Select("select image_url from userinfo where username=#{username}")
	@Result(column="image_url",javaType=String.class)
	public String getImage_url(String username);
	
	@Insert("insert into userinfo(username) values(#{username})")
	public void adduserinfo(String username);
}
