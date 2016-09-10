package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/delete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String user = request.getParameter("username");
			try {
				response.setContentType("text/html");
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_login","saru","sarulatha");
				Statement stmt = conn.createStatement();
				int count = stmt.executeUpdate("delete from login where user = '"+user+"'");
				if(count > 0) {
					out.println("Successfully Deleted");
					out.println("<a href=index.html>Do More</a>");
				} else {
					response.sendRedirect("index.html");
				}
			} catch(Exception e) { 
				out.println(e);
			}
		}
	}
	}


