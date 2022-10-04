package com.college.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.library.entity.User;
import com.college.library.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService theUserService) {
		this.userService = theUserService;
	}
	
	@PostMapping
	public User authenticate(@RequestBody User user) {
		return this.userService.authenticate(user.getLogin(), user.getPassword());
	
	}
	
	@GetMapping("/test/{userId}")
	public String userRole(@PathVariable int userId) {
		User user = this.userService.findOne(userId);
		return user.getRole();
	}
	
}
