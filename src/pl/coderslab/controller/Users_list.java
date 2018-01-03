package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Group;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class Users_list
 */
@WebServlet("/Users_list")
public class Users_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sel = request.getParameter("sel");
		if (sel == null) {
			try {
				Connection conn = DbUtil.getConn();
				User[] users = User.loadAllUsers(conn);
				request.setAttribute("users", users);

			} catch (SQLException e) {

				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/WEB-INF/views/users_list.jsp").forward(request, response);
		}
		if (sel.equals("add")) {
			request.setAttribute("sel", "add");
			getServletContext().getRequestDispatcher("/WEB-INF/views/add_user.jsp").forward(request, response);

		} else if (sel.equals("edit")) {
			request.setAttribute("sel", "edit");
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			getServletContext().getRequestDispatcher("/WEB-INF/views/add_user.jsp").forward(request, response);

		} else if (sel.equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));

			try {
				Connection conn = DbUtil.getConn();

				User user = User.loadUserById(conn, id);
				user.delete(conn);
				User[] users = User.loadAllUsers(conn);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/WEB-INF/views/users_list.jsp").forward(request, response);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String newName = request.getParameter("newName");
		String sel = request.getParameter("sel");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		

		if (sel.equals("add")) {

			try {
				Connection conn = DbUtil.getConn();

				User newUser = new User();
				newUser.setUsername(newName);
				newUser.setPassword(pass);
				newUser.setEmail(email);
				newUser.setUser_group_id(1);
				newUser.saveToDB(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		if (sel.equals("edit")) {
			try {
				int idTmp = Integer.parseInt(id);
				Connection conn = DbUtil.getConn();
				User user = User.loadUserById(conn, idTmp);
				user.setUsername(newName);
				user.setEmail(email);
				user.setPassword(pass);
				user.setUser_group_id(1);
				user.saveToDB(conn);
				
			} catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
			}
		}

		try {
			Connection conn = DbUtil.getConn();

			User[] users = User.loadAllUsers(conn);
			request.setAttribute("users", users);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/users_list.jsp").forward(request, response);

	}

}
