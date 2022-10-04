package com.college.library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_issue")
	private Date dateOfIssue;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "return_date")
	private Date returnDate;
	
	@ManyToOne
	private BookItem bookItem;
	

	@ManyToOne
	private Book book;
	
	@JsonBackReference
	@ManyToOne
	private Student borrower;
	
	private boolean returned;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	
	public BookItem getBookItem() {
		return bookItem;
	}


	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Date getDateOfIssue() {
		return dateOfIssue;
	}


	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public Student getBorrower() {
		return borrower;
	}


	public void setBorrower(Student borrower) {
		this.borrower = borrower;
	}

	

	public Book getBook() {
		return book;
	}


	public boolean isReturned() {
		return returned;
	}


	public void setReturned(boolean returned) {
		this.returned = returned;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", dateOfIssue=" + dateOfIssue + ", returnDate=" + returnDate + ", bookItem="
				+ bookItem + ", borrower=" + borrower + "]";
	}
	
	

}
