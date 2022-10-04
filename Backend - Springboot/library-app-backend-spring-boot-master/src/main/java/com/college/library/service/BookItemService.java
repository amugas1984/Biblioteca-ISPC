package com.college.library.service;

import java.util.List;

import com.college.library.entity.BookItem;

public interface BookItemService {
	public List<BookItem> findAll();

	public BookItem findOne(int barcode);

	public void save(BookItem bookItem);

	public void delete(int barcode);
}
