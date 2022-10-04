package com.college.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	

}
