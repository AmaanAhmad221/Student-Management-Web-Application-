package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.model.Student;

@WebServlet("/ReadStudents")
public class ReadStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='css/table.css'>");
		out.println("</head><body>");

		List<Student> list = dao.getAllStudent();
		if (list == null || list.isEmpty()) {
			out.println("<h3>No students found</h3>");
		} else {
			out.println("<table id='customers'>");
			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Marks</th>");
			out.println("<th>DELETE</th>");
			out.println("<th>UPDATE</th>");
			out.println("</tr>");

			Iterator<Student> itr = list.iterator();
			while (itr.hasNext()) {
				Student student = itr.next();
				out.println("<tr>");
				out.println("<td>" + student.getId() + "</td>");
				out.println("<td>" + student.getName() + "</td>");
				out.println("<td>" + student.getMarks() + "</td>");
				out.println("<td><a href='DeleteStudent?did=" + student.getId() + "'>DELETE</a></td>");
				out.println("<td><a href='UpdateForm?did=" + student.getId() + "'>UPDATE</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}

		out.println("</body></html>");
	}
}
