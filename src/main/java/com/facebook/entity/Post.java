package com.facebook.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;
    private String imageUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Relationship with User
    

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Post(Long id, User user, String content, String imageUrl, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user + ", content=" + content + ", imageUrl=" + imageUrl + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
}

