package com.college.library.service;

import java.util.List;

import com.college.library.entity.Notification;

public interface NotificationService {
	
	public List<Notification> findAll();

	public Notification findOne(int id);

	public void save(Notification notification);

	public void delete(int id);
}
