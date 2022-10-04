package com.college.library.service;

import java.util.List;

import com.college.library.entity.Librarian;

public interface LibrarianService {
	
	public List<Librarian> findAll();

	public Librarian findOne(int uid);

	public void save(Librarian librarian);

	public void delete(int uid);
}
