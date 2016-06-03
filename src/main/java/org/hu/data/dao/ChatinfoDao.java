package org.hu.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.hu.data.model.Chatinfo;

public interface ChatinfoDao {
	@Select("select * from chatinfo order by create_time desc limit 0,10")
	public List<Chatinfo> getAll();
	
	@Insert("insert into chatinfo(username,message,create_time) values(#{username},#{message},now())")
	public void add(Chatinfo chatinfo);
	
	@Select("select * from chatinfo where username!=#{username} and create_time>#{lasttime}")
	public List<Chatinfo> getCurrentChatinfo(Map map);
}
