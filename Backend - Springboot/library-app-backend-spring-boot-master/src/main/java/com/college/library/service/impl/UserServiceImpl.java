package com.college.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.library.dao.UserRepository;
import com.college.library.entity.User;
import com.college.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;


	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
	@Override
	public User findOne(int uid) {
		Optional<User> user = userRepository.findById(uid);
		if (user.isPresent())
			return user.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
	}
	
	@Override
	public User authenticate(String login, String password) {
		User user = userRepository.findByLoginAndPassword(login, password);
		if (user!=null)
			return user;
		else
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login / Password are invalid");
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(int uid) {
		userRepository.deleteById(uid);
	}

}
