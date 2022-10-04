package com.college.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.BookItem;

public interface BookItemRepository extends JpaRepository<BookItem, Integer> {

}
