package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.dao.ReimbDaoImpl;
import com.dao.UserDaoImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Reimbursement;
import com.model.User;

public class ReimbursementRequestController {
	public static String SubmitReimbursement (HttpServletRequest r, HttpServletResponse res) throws IOException {
		String imageStr = r.getParameter("receipt");
//		byte[] decoded = Base64.decodeBase64(imageStr);
		String username = r.getParameter("username");
		float amount = Float.parseFloat(r.getParameter("amount"));
		String type =r.getParameter("type");
		String description= r.getParameter("description");
		int id= Integer.parseInt(r.getParameter("id"));
		
		
		Reimbursement reimbursement = new Reimbursement(amount, description, username, "pending",type );
		
		ReimbDaoImpl impl = new ReimbDaoImpl();
		boolean success = impl.insertReimbursement(reimbursement);
		
        
		ReimbursementResponseClass responseObj= new ReimbursementResponseClass (success);
		res.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));	
		
		return "/html/make-requests.html";
	}
}

final class ReimbursementResponseClass {
	boolean success;
	

	public ReimbursementResponseClass(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

	
}
