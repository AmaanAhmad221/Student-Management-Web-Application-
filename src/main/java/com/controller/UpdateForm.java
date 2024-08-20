package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.model.Student;

@WebServlet("/UpdateForm")
public class UpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Update Student Form</title>");
		out.print("<link rel='stylesheet' href='css/form.css'>"); // Corrected typo from 'stylsheet' to 'stylesheet'
		out.print("</head>");
		out.print("<body>");

		int id = Integer.parseInt(request.getParameter("did"));
		Student student = dao.getStudentById(id);

		out.print("<div class='form-container'>");
		out.print("<h2>STUDENT UPDATE FORM</h2><br>");

		out.print("<form action='UpdateStudent' method='get'>"); // Changed method to 'post' for updating data
		out.print("<input type='hidden' name='eid' value='" + student.getId() + "'>");
		out.print("<br>");
		out.print("<label for='uname'>Name:</label>");
		out.print("<input type='text' id='uname' name='uname' value='" + student.getName() + "' required>");
		out.print("<br>");
		out.print("<label for='marks'>Marks:</label>");
		out.print("<input type='text' id='marks' name='marks' value='" + student.getMarks() + "' required>");
		out.print("<br>");
		out.print("<input type='submit' value='UPDATE'>");
		out.print("</form>");
		out.print("</div>");

		out.print("</body>");
		out.print("</html>");
	}
}
