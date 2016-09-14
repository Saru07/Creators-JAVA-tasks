package com.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LoginRenderServlet
 */
@WebServlet("/login")
public class LoginRenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			response.setContentType("application/json");
			try {
				List<Usrtbl> li = new LinkedList<Usrtbl>();
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/User_Login","root","");
				Statement stmt = conn.createStatement();
				String cuser = request.getParameter("cuser");
				String cpass = request.getParameter("cpass");
				ResultSet rs = stmt.executeQuery("select * from login where user = '"+cuser+"' and password = '"+cpass+"' ");
				
				while(rs.next()) {
					li.add(new Usrtbl(rs.getInt(1),rs.getString(2),rs.getString(3)));
				}
				out.println(new Gson().toJson(li));
			} catch(Exception e) { 
				out.println(e);
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

}
