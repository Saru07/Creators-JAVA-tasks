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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
				PreparedStatement ps = Database.getStatement("delete from usercontact where accountNumber = ?");
				ps.setString(1, accountNumber);
				int val = ps.executeUpdate();
				if(val > 0) {
					mp.put("code", "1");
					mp.put("message", "Delete Success");
				}
				else {
					mp.put("code", "0");
					mp.put("message", "User doesn't exist");
				}
				out.println(new Gson().toJson(mp));
				out.flush();
				out.close();
			} catch(Exception e) {
				mp.put("code", "0");
				mp.put("message", e.toString());
				out.println(new Gson().toJson(mp));
			}
		}
	}

}
