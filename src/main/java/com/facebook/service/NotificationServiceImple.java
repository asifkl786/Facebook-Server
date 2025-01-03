package com.facebook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facebook.NotificationType;
import com.facebook.dto.NotificationRequestDTO;
import com.facebook.dto.NotificationResponseDTO;
import com.facebook.entity.Notification;
import com.facebook.entity.User;
import com.facebook.exception.ResourceNotFoundException;
import com.facebook.mapper.NotificationMapper;
import com.facebook.repository.NotificationRepository;
import com.facebook.repository.UserRepository;


@Service
public class NotificationServiceImple implements NotificationService {

	    private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImple.class);
	
	
	    @Autowired
	    private NotificationRepository notificationRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private NotificationMapper notificationMapper;

	    
	    
	    // Create Notification
		@Override
		public NotificationResponseDTO createNotification(NotificationRequestDTO dto) {
			User recipient = userRepository.findById(dto.getRecipientId())
	                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found with ID: " + dto.getRecipientId()));

	        Notification notification = new Notification();
	        notification.setRecipient(recipient);
	        notification.setContent(dto.getContent());
	        notification.setType(NotificationType.valueOf(dto.getType().toUpperCase()));

	        Notification savedNotification = notificationRepository.save(notification);
	        logger.info("Notification Create Successfully with id : {}",notification.getId());
	        return notificationMapper.toDTO(savedNotification);
		}

		// Get All Notifications for a User
		@Override
		public List<NotificationResponseDTO> getNotifications(Long recipientId) {
			User recipient = userRepository.findById(recipientId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + recipientId));

	        List<Notification> notifications = notificationRepository.findByRecipientOrderByCreatedAtDesc(recipient);
	        logger.info("{} :: Get Notification Successfully ",notifications.size());
	        return notifications.stream().map(notificationMapper::toDTO).collect(Collectors.toList());
		}

		// Mark Notification as Read
		@Override
		public NotificationResponseDTO markAsRead(Long notificationId) {
			Notification notification = notificationRepository.findById(notificationId)
	                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + notificationId));

	        notification.setRead(true);
	        Notification updatedNotification = notificationRepository.save(notification);
	        logger.info("Notification Marks Successfully with id : {}",updatedNotification.getId());
	        return notificationMapper.toDTO(updatedNotification);
		}

		
		// Delete Notification
		@Override
		public void deleteNotification(Long notificationId) {
			Notification notification = notificationRepository.findById(notificationId)
	                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + notificationId));
	        notificationRepository.delete(notification);
	        logger.info("Notification Delete Successfully with id : {}",notification.getId());
	       	
		}
}
