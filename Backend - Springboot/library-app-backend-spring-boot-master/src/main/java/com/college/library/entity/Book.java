package com.college.library.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Pc
 *
 */
@Entity(name = "Book")
@Table(name = "book")
@JsonIgnoreProperties("inspection")
public class Book {

	@Id
	@Column(name = "isbn")
	private int isbn;

	@Column(name = "title")
	private String title;

	private String description;
	
	private String image;
	
	@Column(name = "author")
	private String author;

	@Column(name = "edition")
	private String edition;
	
	private String publisher;

	@Column(name = "number_of_pages")
	private int numberOfPages;

	@Column(name = "language")
	private String language;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean available;

	@JsonIgnore
	@OneToMany(mappedBy = "book")
	private List<BookItem> bookItems;

	@JsonIgnore
	@OneToMany(mappedBy = "book")
	private List<Transaction> transactions;

	@Enumerated(EnumType.STRING)
	private Category category;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<BookItem> getBookItems() {
		return bookItems;
	}

	public void setBookItems(List<BookItem> bookItems) {
		this.bookItems = bookItems;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", image=" + image
				+ ", author=" + author + ", edition=" + edition + ", publisher=" + publisher + ", numberOfPages="
				+ numberOfPages + ", language=" + language + ", availability=" + available + ", category=" + category
				+ "]";
	}

	

	

	

	

}
