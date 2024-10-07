package com.shyam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.app.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
