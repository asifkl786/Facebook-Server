package com.facebook.mapper;

import org.springframework.stereotype.Component;

import com.facebook.dto.FriendshipResponseDTO;
import com.facebook.entity.Friendship;


@Component
public class FriendshipMapper {

	
	public FriendshipResponseDTO toDTO(Friendship friendship) {
        FriendshipResponseDTO dto = new FriendshipResponseDTO();
        dto.setId(friendship.getId());
        dto.setRequesterName(friendship.getRequester().getFirstName() + " " + friendship.getRequester().getLastName());
        dto.setReceiverName(friendship.getReceiver().getFirstName() + " " + friendship.getReceiver().getLastName());
        dto.setStatus(friendship.getStatus().name());
        return dto;
    }
}
