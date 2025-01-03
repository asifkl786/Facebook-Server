package com.facebook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facebook.dto.NotificationRequestDTO;
import com.facebook.dto.NotificationResponseDTO;
import com.facebook.service.NotificationService;

import lombok.AllArgsConstructor;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
    private NotificationService notificationService;
	
	// Create Notification
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(@RequestBody NotificationRequestDTO dto) {
    	logger.info("Recived Request to Create CreateNotification with id: {}",dto.getRecipientId());
        NotificationResponseDTO response = notificationService.createNotification(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
   // Get Notifications for a User
    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getNotifications(@RequestParam Long recipientId) {
    	logger.info("Recived Request to getNotification with id: {}",recipientId);
        List<NotificationResponseDTO> notifications = notificationService.getNotifications(recipientId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
    
   // Mark Notification as Read
    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> markAsRead(@PathVariable Long id) {
    	logger.info("Recived Request to Marks as Read with id: {}",id);
        NotificationResponseDTO response = notificationService.markAsRead(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
   // Delete Notification
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
    	logger.info("Recived Request to Delete Notification with id: {}",id);
        notificationService.deleteNotification(id);
        return new ResponseEntity<>("Delete Notification Successfully", HttpStatus.OK);
    }
}
