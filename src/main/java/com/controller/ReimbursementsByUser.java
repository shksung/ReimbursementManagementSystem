package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReimbDaoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Reimbursement;

public class ReimbursementsByUser {
	public static String GetAllReimbursements (HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		ReimbDaoImpl imp = new ReimbDaoImpl();
		String username = req.getParameter("username"); //this needs to be retrieved from frontend
		
		ArrayList<Reimbursement> reimbursements= imp.selectReimbursementsByUserName(username);
		ReimbUserResponse responseObj = new ReimbUserResponse(reimbursements);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));
		
		return "/html/request-history.html";
		
	}
}

final class ReimbUserResponse {
	private ArrayList r ;

	public ReimbUserResponse(ArrayList r) {
		super();
		this.r = r;
	}

	public ArrayList getR() {
		return r;
	}

	public void setR(ArrayList r) {
		this.r = r;
	}
	
	
	
	
}
