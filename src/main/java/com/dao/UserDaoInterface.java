package com.dao;

import com.model.User;

public interface UserDaoInterface {
	public boolean insertUser(User P);
	public User selectUser(String username, String password);
	public boolean userExists(String username);
	public boolean emailExists(String email);//for checking if username already exists
	public User loginWithEmail (String email, String password);
}
