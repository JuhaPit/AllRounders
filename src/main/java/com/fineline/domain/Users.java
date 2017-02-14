package com.fineline.domain;

public class Users {
	
	private int id;
	private String username;
	private String password;
	private String user_firstname;
	private String user_lastname;
	
	public Users(){
		
		super();
	}

	public Users(int id, String username, String password,
			String user_firstname, String user_lastname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user_firstname = user_firstname;
		this.user_lastname = user_lastname;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_firstname() {
		return user_firstname;
	}

	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}

	public String getUser_lastname() {
		return user_lastname;
	}

	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password="
				+ password + ", user_firstname=" + user_firstname
				+ ", user_lastname=" + user_lastname + "]";
	}
	
	

}
