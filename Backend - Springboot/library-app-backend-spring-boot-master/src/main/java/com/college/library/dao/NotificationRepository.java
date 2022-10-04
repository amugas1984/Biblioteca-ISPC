package com.college.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.library.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
