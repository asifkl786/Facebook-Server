package com.facebook.dto;

public class NotificationResponseDTO {

	    private Long id;
	    private String content;
	    private boolean isRead;
	    private String type;
	    private String createdAt;
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public boolean isRead() {
			return isRead;
		}
		public void setRead(boolean isRead) {
			this.isRead = isRead;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
	    
	    
}
