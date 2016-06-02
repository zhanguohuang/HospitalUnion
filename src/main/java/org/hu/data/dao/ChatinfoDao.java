package org.hu.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.hu.data.model.Chatinfo;

public interface ChatinfoDao {
	@Select("select * from chatinfo")
	public List<Chatinfo> getAll();
	
	@Insert("insert into chatinfo(username,message,create_time) values(#{username},#{message},now())")
	public void add(Chatinfo chatinfo);
}
