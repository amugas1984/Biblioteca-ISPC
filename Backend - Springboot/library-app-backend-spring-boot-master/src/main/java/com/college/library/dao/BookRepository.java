package com.college.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.Book;
import com.college.library.entity.Category;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByCategory(Category category);
}
