package com.facebook.dto;

public class NotificationRequestDTO {
	
	private Long recipientId; // ID of the user receiving the notification
    private String content;   // Notification message
    private String type;      // Notification type (LIKE, COMMENT, FRIEND_REQUEST)
    
    
	public Long getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
       
}


