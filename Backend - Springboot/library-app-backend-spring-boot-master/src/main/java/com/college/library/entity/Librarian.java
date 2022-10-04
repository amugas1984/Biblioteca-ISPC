package com.college.library.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Librarian")
@DiscriminatorValue(value = "Librarian")
public class Librarian extends User {

	public Librarian() {
		// TODO Auto-generated constructor stub
	}
	
}
