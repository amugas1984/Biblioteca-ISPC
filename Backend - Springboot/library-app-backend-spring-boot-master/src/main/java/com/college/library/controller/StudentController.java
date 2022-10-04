package com.college.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.library.entity.Student;
import com.college.library.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/all")
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		Student student = studentService.findOne(studentId);

		if (student == null)
			throw new RuntimeException("student id not found uid - " + studentId);

		return student;
	}
	
	@GetMapping("fromTransaction/{transactionId}")
	public Student getStudentFromTransaction(@PathVariable int transactionId) {
		Student student = studentService.findStudentFromTransaction(transactionId);
		if (student == null)
			throw new RuntimeException("student not found");
		return student;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping
	public Student addStudent(@RequestBody Student student) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		student.setUid(0);

		studentService.save(student);

		return student;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public Student updateStudent(@RequestBody Student student) {

		studentService.save(student);

		return student;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping
	public String deleteStudent(@PathVariable int studentId) {

		Student student = studentService.findOne(studentId);

		// throw exception if null

		if (student == null)
			throw new RuntimeException("student uid not found - " + studentId);

		studentService.delete(studentId);

		return "Deleted student uid - " + studentId;
	}

}
