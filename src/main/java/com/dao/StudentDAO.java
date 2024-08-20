package com.dao;

import com.model.Student;
import java.util.List;

public interface StudentDAO {
	public int saveStudent(Student student);

	public int deleteStudent(int id);

	public int updateStudent(Student student);

	public List<Student> getAllStudent();

	public Student getStudentById(int id);
}
