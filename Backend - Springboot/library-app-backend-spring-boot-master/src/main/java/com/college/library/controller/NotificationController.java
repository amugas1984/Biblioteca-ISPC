package com.college.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.library.entity.Notification;
import com.college.library.service.NotificationService;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins="http://localhost:4200/",maxAge=3600)
public class NotificationController {

	private NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@GetMapping
	public List<Notification> findAll() {
		return notificationService.findAll();
	}

	@GetMapping("/{notificationId}")
	public Notification getNotification(@PathVariable int notificationId) {

		Notification notification = notificationService.findOne(notificationId);

		if (notification == null)
			throw new RuntimeException("Notification id not found - " + notificationId);

		return notification;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping
	public Notification addNotification(@RequestBody Notification notification) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		notification.setId(0);

		notificationService.save(notification);

		return notification;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public Notification updateNotification(@RequestBody Notification notification) {

		notificationService.save(notification);

		return notification;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping
	public String deleteNotification(@PathVariable int notificationId) {

		Notification notification = notificationService.findOne(notificationId);

		// throw exception if null

		if (notification == null)
			throw new RuntimeException("Notification id not found - " + notificationId);

		notificationService.delete(notificationId);

		return "Deleted Notification id - " + notificationId;
	}

}
