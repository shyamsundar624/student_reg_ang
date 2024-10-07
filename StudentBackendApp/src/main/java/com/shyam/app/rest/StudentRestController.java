package com.shyam.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.app.entity.Student;
import com.shyam.app.exception.StudentNotFoundException;
import com.shyam.app.service.IStudentService;

@RestController
@RequestMapping("/v1/api/student")

@CrossOrigin("http://localhost:4200/")
public class StudentRestController {

	@Autowired
	private IStudentService studentService;

	@PostMapping("/create")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		Integer id = studentService.saveStudent(student);
		String msg = "Student '" + id + "' Created";
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> list = studentService.getAllStudent();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Student> getOneStudent(@PathVariable Integer id) {
		ResponseEntity<Student> response = null;
		try {
			Student student = studentService.getStudentById(id);
			response = ResponseEntity.ok(student);
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		ResponseEntity<String> response = null;
		try {
			studentService.deleteStudent(id);
			response = ResponseEntity.ok("Student '"+id+"' Deleted Successfully");
		} catch (StudentNotFoundException e) {
			throw e;
		}
		return response;
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
		String msg = "Student '" + student.getStdId() + "' Updated";
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}
