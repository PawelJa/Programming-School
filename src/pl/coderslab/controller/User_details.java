package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class User_details
 */
@WebServlet("/User_details")
public class User_details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("user_id"));
		try {
			Connection conn = DbUtil.getConn();
			
			User user = User.loadUserById(conn, id);
			Solution[] solutions = Solution.loadAllSolutionsByUserId(conn, id);
			
			request.setAttribute("user", user);
			request.setAttribute("solutions", solutions);
			getServletContext().getRequestDispatcher("/WEB-INF/views/user_details.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
