package com.college.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.library.dao.BookItemRepository;
import com.college.library.entity.BookItem;
import com.college.library.service.BookItemService;

@Service
public class BookItemServiceImpl implements BookItemService {

	private BookItemRepository bookItemRepository;

	public BookItemServiceImpl(BookItemRepository theBookItemRepository) {
		bookItemRepository = theBookItemRepository;
	}

	@Override
	public List<BookItem> findAll() {
		return bookItemRepository.findAll();
	}

	@Override
	public BookItem findOne(int barcode) {
		Optional<BookItem> bookItem = bookItemRepository.findById(barcode);
		if (bookItem.isPresent())
			return bookItem.get();
		else
 			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find bookItem barcode - " + barcode);
	}

	@Override
	public void save(BookItem bookItem) {
		bookItemRepository.save(bookItem);

	}

	@Override
	public void delete(int barcode) {
		if (bookItemRepository.findById(barcode).get()== null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookItem not found");
		bookItemRepository.deleteById(barcode);
	}

}
