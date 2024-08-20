package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Student;
import com.utility.DBUtility;

public class StudentDAOImpl implements StudentDAO {

	@Override
	public int saveStudent(Student student) {
		Connection con = DBUtility.getConnection();
		String sql = "insert into student1 values(?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setDouble(3, student.getMarks());

			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("StudentDAO save " + e);
		}
		return 0;
	}

	@Override
	public int deleteStudent(int id) {
		Connection con = DBUtility.getConnection();
		String sql = "delete from student1 where id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception Occured" + e);
		}
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		Connection con = DBUtility.getConnection();
		String sql = "update student1 set name=?,marks=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setDouble(2, student.getMarks());
			ps.setInt(3, student.getId());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception Occuered" + e);
		}
		return 0;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> list = new ArrayList<Student>();
		Connection con = DBUtility.getConnection();
		String sql = "Select * from student1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setMarks(rs.getDouble(3));
				list.add(student);
			}
		} catch (Exception e) {
			System.out.println("All Record" + e);
		}
		return list;
	}

	@Override
	public Student getStudentById(int id) {
		Connection con = DBUtility.getConnection();
		String sql = "Select*from student1 where id=?";
		Student student = new Student();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setMarks(rs.getInt(3));
			}
		} catch (Exception e) {
			System.out.println("Get Student By Id" + e);
		}
		return student;
	}

}
