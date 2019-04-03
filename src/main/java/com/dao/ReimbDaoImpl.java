package com.dao;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import com.model.Reimbursement;
import com.model.TestBlob;
import com.model.User;

public class ReimbDaoImpl implements ReimbDaoInterface {
	
	static{

	       try {

	           Class.forName("oracle.jdbc.driver.OracleDriver");

	       } catch (ClassNotFoundException e) {

	           e.printStackTrace();

	       }

	   }
	
	private static String url="jdbc:oracle:thin:@sql0304db.clxhqtzlud4f.us-east-2.rds.amazonaws.com:1521:SQL0304";
	private static String username= "ksung12";
	private static String password= "A070694";
	
	@Override
	public boolean insertReimbursement(Reimbursement r) {
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ers_reimbursement (amount, reimb_desc, author, status, reimb_type) VALUES (?,?,?,?,?)");
			ps.setFloat(1, r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setString(3, r.getAuthor());
			ps.setString(4, r.getStatus());
			ps.setString(5, r.getType());
			ps.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Reimbursement> selectReimbursementsByUserName(String author) { //when a user wants to select all their reimbursements
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursement WHERE author=? ");
			ps.setString(1,author);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add (new Reimbursement(rs.getFloat("amount"),rs.getString("reimb_desc"), rs.getString("submitted"),rs.getString("resolved"), rs.getString("author"),rs.getString("e_resolver"), rs.getString("status"), rs.getString("reimb_type"), rs.getInt("reimb_id"))); 
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insertTest(Reimbursement r) { //testing purposes
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ers_reimbursement (amount, reimb_desc) VALUES (?,?)");
			ps.setFloat(1, r.getAmount());
			ps.setString(2, r.getDescription());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public ArrayList<Reimbursement> selectAllReimbursements() throws UnsupportedEncodingException {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursement");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			
				list.add (new Reimbursement(rs.getFloat("amount"),rs.getString("reimb_desc"),
						rs.getString("submitted"), rs.getString("resolved"),
						rs.getString("author"), rs.getString("e_resolver"), 
						rs.getString("status"), rs.getString("reimb_type"), rs.getInt("reimb_id"))); 
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public TestBlob test(int author) {
		//ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		TestBlob blob = null;
		
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursement WHERE author=? ");
			ps.setInt(1,author);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Blob b = rs.getBlob("receipt");
				byte[]blobasbytes= b.getBytes(1, (int) b.length());
				blob = new TestBlob (blobasbytes);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return blob;
	}

	@Override
	public int editReimbursementStatus(int id, String status, String u, Date myObj) { //edits
		int res=0;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE ers_reimbursement SET status=?, e_resolver=?, resolved=? WHERE reimb_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, u);
			ps.setDate(3, myObj);
			ps.setInt(4,  id);
			res = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}

	@Override
	public ArrayList<Reimbursement> selectReimbursementsByStatus(String status) {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursement WHERE status=? ");
			ps.setString(1,status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add (new Reimbursement(rs.getFloat("amount"),rs.getString("reimb_desc"),
						rs.getString("submitted"), rs.getString("resolved"),
						rs.getString("author"), rs.getString("e_resolver"), 
						rs.getString("status"), rs.getString("reimb_type"), rs.getInt("reimb_id"))); 
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
