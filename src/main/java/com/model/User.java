package com.model;

public class User {
	private int id;
	private String username; 
	private String password;
	private String name;
	private String email;
	private String type;
	
	public User(int id,String username, String password, String name, String email, String type) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.type = type;
	}
	
	

	public User(String username, String password, String name, String email, String type) { //without ID
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.type = type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", type=" + type + "]";
	}


	
	
	
}
