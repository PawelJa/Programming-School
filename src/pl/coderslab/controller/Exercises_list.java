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
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class Exercises_list
 */
@WebServlet("/Exercises_list")
public class Exercises_list extends HttpServlet {
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

				Exercise[] exercises = Exercise.loadAllExercises(conn);

				request.setAttribute("exercises", exercises);

			} catch (SQLException e) {

				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/exercises_list.jsp").forward(request, response);
		}

		if (sel.equals("add")) {
			request.setAttribute("sel", "add");
			getServletContext().getRequestDispatcher("/WEB-INF/views/add_exercise.jsp").forward(request, response);

		} else if (sel.equals("edit")) {
			request.setAttribute("sel", "edit");
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			getServletContext().getRequestDispatcher("/WEB-INF/views/add_exercise.jsp").forward(request, response);

		} else if (sel.equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));

			try {
				Connection conn = DbUtil.getConn();

				Exercise exercise = Exercise.loadExerciseById(conn, id);
				exercise.delete(conn);
				Exercise[] exercises = Exercise.loadAllExercises(conn);
				request.setAttribute("exercises", exercises);
				getServletContext().getRequestDispatcher("/WEB-INF/views/exercises_list.jsp").forward(request,
						response);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String newTitle = request.getParameter("newTitle");
		String sel = request.getParameter("sel");
		String description = request.getParameter("description");

		if (sel.equals("add")) {

			try {
				Connection conn = DbUtil.getConn();

				Exercise newExe = new Exercise();
				newExe.setTitle(newTitle);
				newExe.setDescription(description);
				newExe.saveToDB(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		if (sel.equals("edit")) {
			try {
				int idTmp = Integer.parseInt(id);
				Connection conn = DbUtil.getConn();
				Exercise exe = Exercise.loadExerciseById(conn, idTmp);
				exe.setTitle(newTitle);
				exe.setDescription(description);
				exe.saveToDB(conn);

			} catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
			}
		}

		try {
			Connection conn = DbUtil.getConn();

			Exercise[] exercises = Exercise.loadAllExercises(conn);
			request.setAttribute("exercises", exercises);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/exercises_list.jsp").forward(request, response);

	}

}
