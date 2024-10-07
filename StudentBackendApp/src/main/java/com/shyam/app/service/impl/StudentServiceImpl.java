package com.shyam.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.app.entity.Student;
import com.shyam.app.exception.StudentNotFoundException;
import com.shyam.app.repo.StudentRepository;
import com.shyam.app.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repo;

	@Override
	public Integer saveStudent(Student student) {
		student = repo.save(student);
		return student.getStdId();
	}

	@Override
	public void updateStudent(Student student) {
		if (student.getStdId() == null || !repo.existsById(student.getStdId())) {
			throw new StudentNotFoundException("Student '" + student.getStdId() + "' Not Exist");
		}else
		repo.save(student);
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		repo.delete(getStudentById(id));
	}

	@Override
	public Student getStudentById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student '" + id + "' Not Exist"));
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
