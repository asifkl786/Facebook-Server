package com.facebook.service;

import java.util.List;

import com.facebook.dto.UserProfileDTO;
import com.facebook.dto.UserRegistrationDTO;

public interface UserService {
	
	UserProfileDTO registerUser(UserRegistrationDTO  userRegistrationDTO );
	UserProfileDTO login(String email, String password);
	UserProfileDTO getUserProfile(Long userId);
	UserProfileDTO updateUserProfile(Long userId,UserProfileDTO userProfileDTO);
	void deleteUserProfile(Long userId);
    List<UserProfileDTO> searchUserProfile(String query);
}
