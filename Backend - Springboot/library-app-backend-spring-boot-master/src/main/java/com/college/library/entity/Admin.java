package com.college.library.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Admin")
@DiscriminatorValue(value = "Admin")
public class Admin extends User {

	public Admin() {
		// TODO Auto-generated constructor stub
	}

}
