package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserContact
 */
@WebServlet("/UserContact")
public class UserContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()) {
			out.println("<!Doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>BANK MANAGEMENT</title>");
			out.println("<link rel = stylesheet href = tableview.css />");
			out.println("</head>");
			out.println("<body>");
			out.println("<table class=\"info\">");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankManagement","saru","sarulatha");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from usercontact");
				out.println("<tr><th>Identity</th><th>Account Number</th><th>First Name</th><th>Last Name</th><th>Date of birth</th><th>Phone number</th><th>Email</th><th>Photo</th></tr>");
				while(rs.next()) {
					out.println("<tr><td>"+rs.getInt(1) + "</td><td>" + rs.getString(2) +"</td><td>" + rs.getString(3) + "</td><td>"+ rs.getString(4)+ "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8) + "</td></tr>");
				}
			} catch(Exception e) {
				out.println(e);
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
