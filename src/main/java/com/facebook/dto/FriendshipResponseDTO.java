package com.facebook.dto;


public class FriendshipResponseDTO {
    private Long id;
    private String requesterName; // Full name of the requester
    private String receiverName;  // Full name of the receiver
    private String status;        // PENDING, ACCEPTED, REJECTED
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
       
}

