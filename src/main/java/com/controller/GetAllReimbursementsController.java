
package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReimbDaoImpl;
import com.dao.UserDaoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Reimbursement;
import com.model.User;

public class GetAllReimbursementsController {
		
	public static String GetAllReimbursements (HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		ReimbDaoImpl imp = new ReimbDaoImpl();
		String pending = req.getParameter("pending-only");
		
		ArrayList<Reimbursement> reimbursements = null;
		
		switch (pending) {
			case "true":
				reimbursements =imp.selectReimbursementsByStatus("pending");
				break;
			case "false":
				reimbursements =imp.selectAllReimbursements();
				break;
		}
				
		AllReimbursementsResponseClass responseObj;
		
		boolean success;
		String json;

		if(reimbursements == null) {
			success= false;
			responseObj = new AllReimbursementsResponseClass(new ArrayList<Reimbursement>(), success);
			res.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));	
		} else {
			success = true;
			responseObj = new AllReimbursementsResponseClass(reimbursements, success);
			res.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));	
		}
		
		
		return "/html/all-reimbursements.html";
	}
}

final class AllReimbursementsResponseClass {
	ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	boolean success;
	
	public AllReimbursementsResponseClass(ArrayList<Reimbursement> reimbursements, boolean success) {
		super();
		this.reimbursements = reimbursements;
		this.success = success;
	}
	public ArrayList<Reimbursement> getReimbursements() {
		return reimbursements;
	}
	public void setReimbursements(ArrayList<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}

