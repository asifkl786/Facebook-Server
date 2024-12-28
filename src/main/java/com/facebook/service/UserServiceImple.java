package com.facebook.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.facebook.dto.UserProfileDTO;
import com.facebook.dto.UserRegistrationDTO;
import com.facebook.entity.User;
import com.facebook.exception.ResourceNotFoundException;
import com.facebook.mapper.UserMapper;
import com.facebook.repository.UserRepository;



@Service
public class UserServiceImple implements UserService{

	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImple.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
    // Register user
	@Override
	public UserProfileDTO registerUser(UserRegistrationDTO  userRegistrationDTO ) {
		Optional<User> existingUser = userRepository.findByEmail(userRegistrationDTO.getEmail());
		 if (existingUser.isPresent()) {
	            throw new RuntimeException("User already exists!");
	        }
		 
		 // Set Basic field
		 User user = new User();
		    user.setEmail(userRegistrationDTO.getEmail());
	        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Encrypt password
	        user.setFirstName(userRegistrationDTO.getFirstName());
	        user.setLastName(userRegistrationDTO.getLastName());
	        
	        // Save User into database
	        User savedUser = userRepository.save(user);
	        logger.info("User Successfully Registerd with Email :: {}",savedUser.getEmail());      
		return userMapper.toDTO(savedUser);
	}

	
	// Login User
	@Override
	public UserProfileDTO login(String email, String password) {
		// Fetch user by email
		User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password!"));
		// Verify password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }
        logger.info("{} :: User Successfully Login",email,password);
        return userMapper.toDTO(user);
	}

	
	// Fetch UserProfile
	@Override
	public UserProfileDTO getUserProfile(Long userId) {
		User userProfile = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Profile is not exists with given id :"  + userId));
		logger.info("{} :: User Profile Successfully Found",userProfile);
		return userMapper.toDTO(userProfile);
	}

	
	// Update User Profile
	@Override
	public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO userProfileDTO) {
		
		// Fetch User from database
		User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User Profile is not exists with given id :"  + userId));
		
		// Set basic field
		user.setId(userProfileDTO.getId());
		user.setFirstName(userProfileDTO.getFirstName());
		user.setLastName(userProfileDTO.getLastName());
		user.setEmail(userProfileDTO.getEmail());
		user.setProfilePicture(userProfileDTO.getProfilePicture());
		user.setUpdatedAt(new java.util.Date());
		
		// Save user into database
		User updatedUser  = userRepository.save(user);
		logger.info("{}:: User Profile Successfully Updated",updatedUser.getLastName());
		return userMapper.toDTO(updatedUser);
	}

	
	// Delete User Profile 
	@Override
	public void deleteUserProfile(Long userId) {
		User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User Profile is not exists with given id :"  + userId));
		userRepository.delete(user);
		logger.info("User Profile Successfully Deleted:: {}",user.getEmail());	
	}

	// Search User Profile By field
	@Override
	public List<UserProfileDTO> searchUserProfile(String query) {
		List<User> userProfiles = userRepository.searchUserProfile(query);
		if (userProfiles.isEmpty()) {
            throw new ResourceNotFoundException("No profile found in with query: " + query);
        }
		//.orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id :"  + id));
		logger.info("{}:: UserProfile Successfully Found",userProfiles.size());
		return userProfiles.stream().map(userMapper::toDTO).collect(Collectors.toList());
	}
	
	

}
