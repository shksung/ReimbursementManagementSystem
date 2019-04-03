package com.controller;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReimbDaoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.TestBlob;

public class TestController {
	
	public static String Test(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		ReimbDaoImpl imp = new ReimbDaoImpl();
		TestBlob blob = imp.test(61);
		response.getWriter().write(new ObjectMapper().writeValueAsString(blob));	
		return "/html/all-requests.html";
	}
}
