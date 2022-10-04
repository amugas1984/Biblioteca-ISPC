package com.college.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.library.dao.LibrarianRepository;
import com.college.library.entity.Librarian;
import com.college.library.service.LibrarianService;

@Service
public class LibrarianServiceImpl implements LibrarianService{

	private LibrarianRepository librarianRepository;

	public LibrarianServiceImpl(LibrarianRepository librarianRepository) {
		this.librarianRepository= librarianRepository;
	}

	@Override
	public List<Librarian> findAll() {
		return librarianRepository.findAll();
	}

	@Override
	public Librarian findOne(int librarianId) {
		Optional<Librarian> librarian= librarianRepository.findById(librarianId);
		if (librarian.isPresent())
			return librarian.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Librarian Not Found");
	}

	@Override
	public void save(Librarian librarian) {
		librarianRepository.save(librarian);

	}

	@Override
	public void delete(int librarianId) {
		if (librarianRepository.findById(librarianId).get()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Librarian Not Found");
		librarianRepository.deleteById(librarianId);
	}

}
