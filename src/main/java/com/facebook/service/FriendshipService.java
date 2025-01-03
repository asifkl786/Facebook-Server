package com.facebook.service;

import java.util.List;

import com.facebook.dto.FriendshipRequestDTO;
import com.facebook.dto.FriendshipResponseDTO;

public interface FriendshipService {

	FriendshipResponseDTO sendFriendRequest(Long requesterId, FriendshipRequestDTO dto);
	FriendshipResponseDTO acceptFriendRequest(Long friendshipId);
	FriendshipResponseDTO rejectFriendRequest(Long friendshipId);
	List<FriendshipResponseDTO> viewFriendRequests(Long receiverId);
	List<FriendshipResponseDTO> listFriends(Long userId);
	void removeFriend(Long friendshipId);
}
