package com.facebook.entity;

import java.util.Date;

import com.facebook.NotificationType;

import jakarta.persistence.*;


@Entity
@Table(name = "notifications")
public class Notification {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content; // Notification message (e.g., "User A liked your post")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient; // User who receives the notification

    @Column(nullable = false)
    private boolean isRead = false; // Read status

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(); // Timestamp for the notification

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // Like, Comment, FriendRequest

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(Long id, String content, User recipient, boolean isRead, Date createdAt,
			NotificationType type) {
		super();
		this.id = id;
		this.content = content;
		this.recipient = recipient;
		this.isRead = isRead;
		this.createdAt = createdAt;
		this.type = type;
	}

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

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", content=" + content + ", recipient=" + recipient + ", isRead=" + isRead
				+ ", createdAt=" + createdAt + ", type=" + type + "]";
	}
   
}
