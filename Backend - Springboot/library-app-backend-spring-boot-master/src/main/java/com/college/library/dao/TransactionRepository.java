package com.college.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.Book;
import com.college.library.entity.BookItem;
import com.college.library.entity.Student;
import com.college.library.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByBorrower(Student borrower);
	List<Transaction> findByBook(Book book);
	Transaction findByBookItemAndBorrowerAndReturned(BookItem bookItem,Student borrower, boolean returned);
}
