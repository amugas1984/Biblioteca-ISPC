package com.college.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByLoginAndPassword(String login, String password);

}
