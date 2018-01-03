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
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Solution_details")
public class Solution_details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("user_id"));
		try {
			Connection conn = DbUtil.getConn();

			Solution solution = Solution.loadSolutionById(conn, id);
			request.setAttribute("solution", solution);
			
			getServletContext().getRequestDispatcher("/WEB-INF/views/solution_details.jsp").forward(request, response);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
