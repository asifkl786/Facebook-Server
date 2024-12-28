package com.facebook.mapper;

import org.springframework.stereotype.Component;

import com.facebook.dto.UserProfileDTO;
import com.facebook.entity.User;


@Component
public class UserMapper {

	// Convert to Entity
    public User toEntity(UserProfileDTO  userProfileDTO) {
        User user = new User();
      user.setId(userProfileDTO.getId());
      user.setFirstName(userProfileDTO.getFirstName());
      user.setLastName(userProfileDTO.getLastName());
      user.setEmail(userProfileDTO.getEmail());
      user.setProfilePicture(userProfileDTO.getProfilePicture());
        return user;
    }

    
    // Convert to DTO
    public UserProfileDTO  toDTO(User user) {
    	UserProfileDTO  userProfileDTO = new UserProfileDTO ();
    	userProfileDTO.setId(user.getId());
    	userProfileDTO.setFirstName(user.getFirstName());
    	userProfileDTO.setLastName(user.getLastName());
    	userProfileDTO.setEmail(user.getEmail());
    	userProfileDTO.setProfilePicture(user.getProfilePicture());
        return userProfileDTO;
    }
}

