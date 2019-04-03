package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestHelper {
	public static String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		switch(request.getRequestURI()) {
			case "/ReimbursementApplication/html/register.do":
				return RegisterController.Register(request, response);
			case "/ReimbursementApplication/html/login.do":
				return LoginController.Login(request, response);
			case "/ReimbursementApplication/html/submit-reimbursement.do":
				return ReimbursementRequestController.SubmitReimbursement(request, response);
			case "/ReimbursementApplication/html/get-all-reimbursements.do":
				return GetAllReimbursementsController.GetAllReimbursements(request, response);
			case "/ReimbursementApplication/html/blob-test.do":
				return TestController.Test(request, response);
				
			case "/ReimbursementApplication/html/get-my-requests.do":
				return ReimbursementsByUser.GetAllReimbursements(request, response);
			case "/ReimbursementApplication/html/approve-deny.do":	
				return StatusUpdateController.updateReimbursementStatus(request, response);
			default:
				return "/html/Login.html";
		}
} }
