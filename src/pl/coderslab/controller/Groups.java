package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Group;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class Groups
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Connection conn = DbUtil.getConn();
			
			Group[] groups = Group.loadAllGroups(conn);
			
			request.setAttribute("groups", groups);
			getServletContext().getRequestDispatcher("/WEB-INF/views/user_groups.jsp").forward(request, response);
			

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
