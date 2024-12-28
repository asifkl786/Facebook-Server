package com.facebook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facebook.dto.UserProfileDTO;
import com.facebook.dto.UserRegistrationDTO;
import com.facebook.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
	
	
	// Build Registration REST API
	@PostMapping("/register")
	public ResponseEntity<UserProfileDTO> register(@RequestBody UserRegistrationDTO  userRegistrationDTO){
		logger.info("Recived Request to Register User With Email:: {}",userRegistrationDTO.getEmail());
		UserProfileDTO userRegister = userService.registerUser(userRegistrationDTO);
		return new ResponseEntity<>(userRegister,HttpStatus.CREATED);
	}
	
	// Build Login REST API
	// User Login
    @GetMapping("/login")
    public ResponseEntity<UserProfileDTO> login(@RequestParam String email, @RequestParam String password) {
    	logger.info("Recived Request to Login User With Email:: {}",email,password);
        UserProfileDTO loggedInUser = userService.login(email, password);
        return new ResponseEntity<>(loggedInUser,HttpStatus.OK);
    } 
	
    // Build Fetch UserProfile REST  API
    @GetMapping("/getProfile/{userId}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Long userId){
    	logger.info("Recived Request to get Profile With Id: {}",userId);
    	UserProfileDTO user = userService.getUserProfile(userId);
    	return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    // Build Update UserProfile REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO){
    	logger.info("Recived Request to update user Profile With Id :{}",id);
    	UserProfileDTO updatedUser = userService.updateUserProfile(id, userProfileDTO);
    	return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    // Build Delete UserProfile REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long id){
    	logger.info("Recived Request to delete User Profile With id:{}",id);
    	userService.deleteUserProfile(id);
    	return new ResponseEntity<>("User Profile Successfully Deleted",HttpStatus.OK);
    }
    
    // Build Search UserProfile REST API
    @GetMapping("/search")
    public ResponseEntity<List<UserProfileDTO>> searchProfile(@RequestParam("query") String query){
    	logger.info("Recived Request To Search Profile With :: {}",query);
    	List<UserProfileDTO> userProfile = userService.searchUserProfile(query);
    	return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
