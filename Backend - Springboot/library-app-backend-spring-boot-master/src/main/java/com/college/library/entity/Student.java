package com.college.library.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Student")
@DiscriminatorValue(value = "Student")
public class Student extends User {

	public Student() {
		// TODO Auto-generated constructor stub
	}
	@JsonManagedReference
	@OneToMany(mappedBy = "borrower")
	private List<Transaction> transactions;
}
