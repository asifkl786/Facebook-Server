package com.facebook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facebook.FriendshipStatus;
import com.facebook.dto.FriendshipRequestDTO;
import com.facebook.dto.FriendshipResponseDTO;
import com.facebook.entity.Friendship;
import com.facebook.entity.User;
import com.facebook.exception.ResourceNotFoundException;
import com.facebook.mapper.FriendshipMapper;
import com.facebook.repository.FriendshipRepository;
import com.facebook.repository.UserRepository;


@Service
public class FriendshipServiceImple implements FriendshipService{
     
	private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImple.class);
	
	
	@Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipMapper friendshipMapper;
    
    
    // Send a Friend Request
	@Override
	public FriendshipResponseDTO sendFriendRequest(Long requesterId, FriendshipRequestDTO dto) {
		User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new ResourceNotFoundException("Requester not found with ID: " + requesterId));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("Receiver not found with ID: " + dto.getReceiverId()));
        if (friendshipRepository.findByRequesterIdAndStatus(requesterId, FriendshipStatus.PENDING)
                .stream().anyMatch(f -> f.getReceiver().equals(receiver))) {
            throw new RuntimeException("Friend request already sent.");
        }
        Friendship friendship = new Friendship();
        friendship.setRequester(requester);
        friendship.setReceiver(receiver);
        friendship.setStatus(FriendshipStatus.PENDING);

        Friendship savedFriendship = friendshipRepository.save(friendship);
        logger.info("Friend Request Successfully Send With id: {}",savedFriendship.getId());
        return friendshipMapper.toDTO(savedFriendship);
    }
	

	// Accept Friend Request 
	@Override
	public FriendshipResponseDTO acceptFriendRequest(Long friendshipId) {
		Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Friend request not found"));

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        Friendship updatedFriendship = friendshipRepository.save(friendship);
        logger.info("Friend Request Successfully Accept With Id : {}",friendshipId);
        return friendshipMapper.toDTO(updatedFriendship);
	}

	// Rejected Friend Request
	@Override
	public FriendshipResponseDTO rejectFriendRequest(Long friendshipId) {
		Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Friend request not found"));
		
		 friendship.setStatus(FriendshipStatus.REJECTED);
	        Friendship updatedFriendship = friendshipRepository.save(friendship);
	        logger.info("Friend Request Successfully Rejected With Id : {}",friendshipId);
	        return friendshipMapper.toDTO(updatedFriendship);
	}

	// View Pending Friend Requests 
	@Override
	public List<FriendshipResponseDTO> viewFriendRequests(Long receiverId) {
		List<Friendship> pendingRequests = friendshipRepository.findByReceiverIdAndStatus(receiverId, FriendshipStatus.PENDING);
		if (pendingRequests.isEmpty()) {
            throw new ResourceNotFoundException("No Request found in with reciverId:{} " + receiverId);
        }
		//.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		logger.info("{}:: Pending Request Successfully Found :",pendingRequests.size());
		return pendingRequests.stream().map(friendshipMapper::toDTO).collect(Collectors.toList());
	}

	// List All Friends
	@Override
	public List<FriendshipResponseDTO> listFriends(Long userId) {
		List<Friendship> friendships = friendshipRepository.findByRequesterIdOrReceiverIdAndStatus(userId, userId, FriendshipStatus.ACCEPTED);
		logger.info("{}:: Friends Successfully Found",friendships.size());
		return friendships.stream().map(friendshipMapper::toDTO).collect(Collectors.toList());
	}

	// Remove Friend 
	@Override
	public void removeFriend(Long friendshipId) {
		Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found"));
        friendshipRepository.delete(friendship);
        logger.info("Friend Delete Successfully With Id::{}",friendship.getId());
		
	}

}
