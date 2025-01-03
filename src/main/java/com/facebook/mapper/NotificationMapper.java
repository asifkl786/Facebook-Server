package com.facebook.mapper;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.facebook.dto.NotificationResponseDTO;
import com.facebook.entity.Notification;


@Component
public class NotificationMapper {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	
	public NotificationResponseDTO toDTO(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setContent(notification.getContent());
        dto.setRead(notification.isRead());
      //  dto.setIsRead(notification.isRead());
        dto.setType(notification.getType().name());
        dto.setCreatedAt(dateFormat.format(notification.getCreatedAt()));
        return dto;
    }
}
