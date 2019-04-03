package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;

public class LoginController {
		
	public static String Login (HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDaoImpl imp = new UserDaoImpl();
		
		User user =imp.loginWithEmail(email, password);
		ResponseClass responseObj;
		
		boolean success;
		String json ;

		if(user == null) {
			success= false;
			responseObj = new ResponseClass(user, success);
			res.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));	
		} else {
			success = true;
			responseObj = new ResponseClass(user, success);
			res.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));	
		}
		
		
		return "/html/login.html";
	}
}

final class ResponseClass {
	User user;
	boolean success;
	public ResponseClass(User user, boolean success) {
		super();
		this.user = user;
		this.success = success;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}


