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

import com.college.library.entity.Librarian;
import com.college.library.service.LibrarianService;

@RestController
@RequestMapping("/api/librarian")
@CrossOrigin
public class LibrarianController {

	private LibrarianService librarianService;

	@Autowired
	public LibrarianController(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}

	@GetMapping
	public List<Librarian> findAll() {
		return librarianService.findAll();
	}

	
	@GetMapping("/{librarianId}")
	public Librarian getLibrarian(@PathVariable int librarianId) {

		Librarian librarian = librarianService.findOne(librarianId);

		if (librarian == null)
			throw new RuntimeException("Librarian id not found uid - " + librarianId);

		return librarian;
	}

	@PostMapping
	public Librarian addLibrarian(@RequestBody Librarian librarian) {

		librarian.setUid(0);

		librarianService.save(librarian);

		return librarian;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public Librarian updateLibrarian(@RequestBody Librarian librarian) {

		librarianService.save(librarian);

		return librarian;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping
	public String deleteLibrarian(@PathVariable int librarianId) {

		Librarian librarian = librarianService.findOne(librarianId);

		// throw exception if null

		if (librarian == null)
			throw new RuntimeException("librarian uid not found - " + librarianId);

		librarianService.delete(librarianId);

		return "Deleted librarian uid - " + librarianId;
	}

}
