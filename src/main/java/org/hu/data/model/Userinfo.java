package org.hu.data.model;

public class Userinfo {
	private int id;
	private String username;
	private int login_failure_count;
	private String image_url;
	private int ip;
	public Userinfo(){};
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
	public int getLogin_failure_count() {
		return login_failure_count;
	}
	public void setLogin_failure_count(int login_failure_count) {
		this.login_failure_count = login_failure_count;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
}
