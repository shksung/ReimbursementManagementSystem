package com.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReimbDaoImpl;

public class StatusUpdateController {
	public static String updateReimbursementStatus(HttpServletRequest req, HttpServletResponse res) {
		String status = req.getParameter("action");
		int id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		Date myObj = new Date(Calendar.getInstance().getTime().getTime());
		
		
		//String id;
		
		
		ReimbDaoImpl imp = new ReimbDaoImpl();
		imp.editReimbursementStatus(id, status,username, myObj);
		
		return "/html/all-reimbursements.html";
	}
}
