package com.facebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facebook.FriendshipStatus;
import com.facebook.entity.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
	
	
	List<Friendship> findByReceiverIdAndStatus(Long receiverId, FriendshipStatus status);

    List<Friendship> findByRequesterIdAndStatus(Long requesterId, FriendshipStatus status);

    List<Friendship> findByRequesterIdOrReceiverIdAndStatus(Long userId1, Long userId2, FriendshipStatus status);

}
