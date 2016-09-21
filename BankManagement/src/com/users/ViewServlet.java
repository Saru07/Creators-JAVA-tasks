package com.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.users.java.Database;
import com.users.java.UserContact;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			PreparedStatement ps = Database.getStatement("select * from usercontact");
			try {
				ResultSet rs = ps.executeQuery();
				List<UserContact> li = new LinkedList<UserContact>();
				while(rs.next()) {
					li.add(new UserContact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7)));
				}
				out.println(new Gson().toJson(li));
				out.flush();
				out.close();
			} catch(Exception e) {
				out.println(e);
			}
			
		}catch(Exception e) {}
	}

}
