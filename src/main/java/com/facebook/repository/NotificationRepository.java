package com.facebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facebook.entity.Notification;
import com.facebook.entity.User;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

	// Fetch all notifications for a user
    List<Notification> findByRecipientOrderByCreatedAtDesc(User recipient);
}
