package org.hu.data.model;


public class Chatinfo {
	private int id;
	private String username;
	private String message;
	private String create_time;
	//范式不匹配，需要增加一个image_url
	private String image_url;
	public Chatinfo(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String toString(){
		return "id="+id+"; username="+username+"; message="+message+"; create_time="+create_time+";";
	}
}
