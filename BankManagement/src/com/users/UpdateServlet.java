package com.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
				PreparedStatement ps = Database.getStatement("update usercontact set firstName = ?, lastName=?, dateOfBirth=?, phoneNumber = ?, email =? where accountNumber = ?");
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setString(3, dateOfBirth);
				ps.setString(4, phoneNumber);
				ps.setString(5, email);
				ps.setString(6, accountNumber);
				int val = ps.executeUpdate();
				if(val > 0) {
					mp.put("code", "1");
					mp.put("message", "Update Success");
				} else {
					mp.put("code", "0");
					mp.put("message", "Username Doesn't Exist");
				}
				out.println(new Gson().toJson(mp));
				out.flush();
				out.close();
			}catch (Exception e) {
				mp.put("code", "0");
				mp.put("message", e.toString());
				out.println(new Gson().toJson(mp));
			}
		}
	}

}
