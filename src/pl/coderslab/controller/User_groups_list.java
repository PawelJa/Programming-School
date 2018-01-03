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
 * Servlet implementation class User_groups_list
 */
@WebServlet("/User_groups_list")
public class User_groups_list extends HttpServlet {
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
				Group[] user_groups = Group.loadAllGroups(conn);
				request.setAttribute("groups", user_groups);

			} catch (SQLException e) {

				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/user_groups_list.jsp").forward(request, response);
		}

		if (sel.equals("add")) {
			request.setAttribute("sel", "add");
			getServletContext().getRequestDispatcher("/WEB-INF/views/add_user_group.jsp").forward(request, response);
		
		} else if (sel.equals("edit")) {
			request.setAttribute("sel", "edit");
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			getServletContext().getRequestDispatcher("/WEB-INF/views/add_user_group.jsp").forward(request, response);
		
		} else if (sel.equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));

			try {
				Connection conn = DbUtil.getConn();

				Group group = Group.loadGroupById(conn, id);
				group.delete(conn);
				Group[] user_groups = Group.loadAllGroups(conn);
				request.setAttribute("groups", user_groups);
				getServletContext().getRequestDispatcher("/WEB-INF/views/user_groups_list.jsp").forward(request,
						response);

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

		if (sel.equals("add")) {

			try {
				Connection conn = DbUtil.getConn();

				Group newGroup = new Group(newName);
				newGroup.setName(newName);
				newGroup.saveToDB(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		if (sel.equals("edit")) {
			try {
				int idTmp = Integer.parseInt(id);
				Connection conn = DbUtil.getConn();
				Group group = Group.loadGroupById(conn, idTmp);
				group.setName(newName);
				group.saveToDB(conn);
			} catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
			}
		}

		try {
			Connection conn = DbUtil.getConn();

			Group[] user_groups = Group.loadAllGroups(conn);
			request.setAttribute("groups", user_groups);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/user_groups_list.jsp").forward(request, response);
	}

}
