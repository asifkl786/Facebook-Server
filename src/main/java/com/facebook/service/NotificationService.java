package com.facebook.service;

import java.util.List;

import com.facebook.dto.NotificationRequestDTO;
import com.facebook.dto.NotificationResponseDTO;

public interface NotificationService {
	
	NotificationResponseDTO createNotification(NotificationRequestDTO dto);
	List<NotificationResponseDTO> getNotifications(Long recipientId);
	NotificationResponseDTO markAsRead(Long notificationId);
	void deleteNotification(Long notificationId);

}
