package com.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.users.java.Database;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			response.setContentType("application/json");
			Map<String, String> mp = new TreeMap<String, String>();
			try {
				String accountNumber = request.getParameter("accountNumber");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String dateOfBirth = request.getParameter("dateOfBirth");
				String phoneNumber = request.getParameter("phoneNumber");
				String email = request.getParameter("email");
				PreparedStatement ps = Database.getStatement("select * from usercontact where accountNumber = ?");
				ps.setString(1, accountNumber);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					mp.put("code", "0");
					mp.put("message", "Existing user");
				}
				else {
					ps = Database.getStatement("insert into usercontact values(null,?,?,?,?,?,?)");					
					ps.setString(1, accountNumber);
					ps.setString(2, firstName);
					ps.setString(3, lastName);
					ps.setString(4, dateOfBirth);
					ps.setString(5, phoneNumber);
					ps.setString(6, email);
					ps.executeUpdate();
					mp.put("code", "1");
					mp.put("message", "Successfully Inserted");
				}
				out.println(new Gson().toJson(mp));
				out.flush();
				out.close();
			}
			catch(Exception e) {
				mp.put("code", "0");
				mp.put("message", e.toString());
				out.println(new Gson().toJson(mp));
			}
		}catch(Exception e){}
	}

}
