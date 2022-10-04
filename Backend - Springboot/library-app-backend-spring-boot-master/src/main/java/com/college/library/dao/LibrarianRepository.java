package com.college.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

}
