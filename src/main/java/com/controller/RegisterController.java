package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;

public class RegisterController {
	public static String Register (HttpServletRequest r, HttpServletResponse res) throws IOException {
	String email = r.getParameter("email");
	String username =r.getParameter("uname");
	String fullname= r.getParameter("fullname");
	String pass = r.getParameter("pass");
	
	User user = new User(username, pass, fullname, email,"employee");
	UserDaoImpl imp = new UserDaoImpl ();
		
	boolean userExists=	imp.userExists(username);
	boolean emailExists= imp.emailExists(email);
	boolean success;
	
	if (!userExists && !emailExists) {
		success = imp.insertUser(user);
	}
	else {
		success= false;
	}
	
	ObjectMapper objectMapper = new ObjectMapper ();
	
	String json = "{\"user-exists\":" + userExists + ", \"email-exists\":" + emailExists +  ", \"success\" : " + success + "}";
	res.getWriter().print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
	

		
		return "/html/register.html";
	}
}
