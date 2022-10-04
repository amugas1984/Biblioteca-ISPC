package com.college.library.service;

import java.util.List;

import com.college.library.entity.Student;

public interface StudentService {
	public List<Student> findAll();

	public Student findOne(int uid);

	public void save(Student student);

	public void delete(int uid);

	Student findStudentFromTransaction(int id);
}
