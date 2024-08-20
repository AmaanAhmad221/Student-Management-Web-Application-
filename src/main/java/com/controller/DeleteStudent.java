package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StudentDAO;
import com.dao.StudentDAOImpl;
import com.utility.DBUtility;

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
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
		String id = request.getParameter("did");
		int id1 = Integer.parseInt(id);

		try {
			Connection con = DBUtility.getConnection();
			int i = dao.deleteStudent(id1);
			if (i > 0) {
				out.print("<b>RECORD DELETED SUCCESS !!!!</b>");
				RequestDispatcher rd = request.getRequestDispatcher("/ReadStudents");

				rd.include(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
