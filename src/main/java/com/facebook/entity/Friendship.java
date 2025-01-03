package com.facebook.entity;

import com.facebook.FriendshipStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "friendships")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester; // The user who sent the friend request

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; // The user who receives the friend request

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendshipStatus status; // PENDING, ACCEPTED, REJECTED

	public Friendship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Friendship(Long id, User requester, User receiver, FriendshipStatus status) {
		super();
		this.id = id;
		this.requester = requester;
		this.receiver = receiver;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public FriendshipStatus getStatus() {
		return status;
	}

	public void setStatus(FriendshipStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Friendship [id=" + id + ", requester=" + requester + ", receiver=" + receiver + ", status=" + status
				+ "]";
	}
    
    
}
