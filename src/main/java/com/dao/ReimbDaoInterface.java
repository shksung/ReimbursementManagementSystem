package com.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.model.Reimbursement;
import com.model.TestBlob;

public interface ReimbDaoInterface {
	public boolean insertReimbursement(Reimbursement r);
	public int insertTest(Reimbursement r);
	public ArrayList selectReimbursementsByUserName(String author);
	public ArrayList<Reimbursement> selectAllReimbursements() throws UnsupportedEncodingException;
	public int editReimbursementStatus(int id, String s, String username, Date date);
	public ArrayList<Reimbursement> selectReimbursementsByStatus(String status);
	
	public TestBlob test (int author); 
}



