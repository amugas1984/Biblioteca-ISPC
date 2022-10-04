package com.college.library.entity;

import org.springframework.web.multipart.MultipartFile;

public class EmbeddedBookFile {

	private Book book;
	
	//private MultipartFile image;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
/*
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "EmbeddedBookFile [book=" + book + ", image=" + image + "]";
	}
*/	
	
	
}
