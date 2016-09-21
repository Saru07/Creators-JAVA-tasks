package com.users.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankManagement", "root", "nopass");
					return conn;
		} catch(Exception e) {
			return null;
		}		
	}
	
	public static PreparedStatement getStatement(String sql) {
		try {
			return getConn().prepareStatement(sql);
		} catch(Exception e){
			return null;
		}
	}
}
