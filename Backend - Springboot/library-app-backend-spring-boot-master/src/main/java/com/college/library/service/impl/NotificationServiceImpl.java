package com.college.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.library.dao.NotificationRepository;
import com.college.library.entity.Notification;
import com.college.library.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	private NotificationRepository notificationRepository;

	@Autowired
	public NotificationServiceImpl(NotificationRepository theNotificationRepository) {
		notificationRepository = theNotificationRepository;
	}

	@Override
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}

	// DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
	@Override
	public Notification findOne(int id) {
		Optional<Notification> notification = notificationRepository.findById(id);
		if (notification.isPresent())
			return notification.get();
		else
			throw new RuntimeException("Did not find notification id - " + id);
	}

	@Override
	public void save(Notification notification) {
		notificationRepository.save(notification);
	}

	@Override
	public void delete(int id) {
		notificationRepository.deleteById(id);

	}

}
