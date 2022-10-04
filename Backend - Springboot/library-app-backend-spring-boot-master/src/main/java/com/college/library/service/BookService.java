package com.college.library.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.college.library.entity.Book;
import com.college.library.entity.Category;

public interface BookService {
	
	public List<Book> findAll();

	public Book findOne(int isbn);

	public void save(Book book);

	public void delete(int isbn);

	int checkAndUpdateBookAvailability(int isbn);

	List<Book> findBookByCategory(Category category);

	void saveImage(MultipartFile image) throws Exception;
}
