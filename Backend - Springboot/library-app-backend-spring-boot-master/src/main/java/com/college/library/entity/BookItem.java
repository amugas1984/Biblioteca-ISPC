package com.college.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookItem {
	

	@Id
	@Column(name = "barcode")
	private int barcode;
	
	@ManyToOne
	private Book book;

	public BookItem() {
		// TODO Auto-generated constructor stub
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "BookItem [barcode=" + barcode + ", book=" + book + "]";
	}
	
	
	
	

}
