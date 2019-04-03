package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class UserDaoImpl implements UserDaoInterface {
	
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
	public boolean insertUser(User p) {
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ers_users (username, user_pass, user_name, user_email, user_role) VALUES (?,?,?,?,?)");
			ps.setString(1, p.getUsername());
			ps.setString(2, p.getPassword());
			ps.setString(3, p.getName());
			ps.setString(4, p.getEmail());
			ps.setString(5, p.getType());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User selectUser(String userName, String passWord) {
		User user= null;
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE username=? AND user_pass=? ");
			ps.setString(1,userName);
			ps.setString(2,passWord);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user= new User(rs.getInt("user_id"),rs.getString("username"), rs.getString("user_pass"), rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_role")); // from the result set, get the name and type and store it into the pet object
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public boolean userExists(String userName) {
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE username=?");
			ps.setString(1,userName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean emailExists(String email) {
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE user_email=?");
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User loginWithEmail(String email, String passWord) {
		User user= null;
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE (username=? OR user_email=?)AND user_pass=? ");
			ps.setString(1,email);
			ps.setString(2,email);
			ps.setString(3,passWord);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user= new User(rs.getInt("user_id"),rs.getString("username"), rs.getString("user_pass"), rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_role")); // from the result set, get the name and type and store it into the pet object
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
