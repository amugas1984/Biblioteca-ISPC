package com.college.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.library.dao.StudentRepository;
import com.college.library.entity.Student;
import com.college.library.entity.Transaction;
import com.college.library.service.StudentService;
import com.college.library.service.TransactionService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private TransactionService transactionService;

	public StudentServiceImpl(StudentRepository studentRepository,TransactionService theTransactionService) {
		this.studentRepository= studentRepository;
		this.transactionService = theTransactionService;
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findOne(int studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if (student.isPresent())
			return student.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
	}

	@Override
	public Student findStudentFromTransaction(int id) {
		Transaction transaction = transactionService.findOne(id);
		Student student = (Student) this.findOne(transaction.getBorrower().getUid());
		
		return student;
	}
	
	@Override
	public void save(Student student) {
		studentRepository.save(student);

	}

	@Override
	public void delete(int studentId) {
		if (studentRepository.findById(studentId).get()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
		studentRepository.deleteById(studentId);
	}

}
