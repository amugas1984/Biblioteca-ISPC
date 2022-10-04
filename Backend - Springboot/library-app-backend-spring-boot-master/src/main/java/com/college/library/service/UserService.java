package com.college.library.service;

import java.util.List;

import com.college.library.entity.User;

public interface UserService {
	public List<User> findAll();

	public User findOne(int uid);

	public void save(User user);

	public void delete(int uid);

	User authenticate(String login, String password);
	
}
