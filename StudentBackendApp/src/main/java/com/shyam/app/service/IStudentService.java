package com.shyam.app.service;

import java.util.List;

import com.shyam.app.entity.Student;

public interface IStudentService {

	Integer saveStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(Integer id);

	Student getStudentById(Integer id);

	List<Student> getAllStudent();
}
