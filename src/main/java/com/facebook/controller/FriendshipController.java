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

import com.facebook.dto.FriendshipRequestDTO;
import com.facebook.dto.FriendshipResponseDTO;
import com.facebook.service.FriendshipService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendshipController.class);
	
	@Autowired
    private FriendshipService friendshipService;
	
	// Build sendFriendRequest REST API
    @PostMapping("/request")
    public ResponseEntity<FriendshipResponseDTO> sendFriendRequest(
            @RequestParam Long requesterId,
            @RequestBody FriendshipRequestDTO dto) {
    	logger.info("Recived Request to sendFriendRequest with Id:: {}",dto.getReceiverId());
    	FriendshipResponseDTO request = friendshipService.sendFriendRequest(requesterId, dto);
    	return new ResponseEntity<>(request, HttpStatus.OK);   
    	}

    // Build AcceptFriend Request REST API
    @PutMapping("/accept/{id}")
    public ResponseEntity<FriendshipResponseDTO> acceptFriendRequest(@PathVariable Long id){
    	logger.info("Recived Request to acceptFriend request with id : {}",id);
    	FriendshipResponseDTO acceptRequest = friendshipService.acceptFriendRequest(id);
    	return new ResponseEntity<>(acceptRequest, HttpStatus.OK);
    }
    
    // Build RejectedFriend Request REST API
    @PutMapping("/rejected/{id}")
    public ResponseEntity<FriendshipResponseDTO> rejectFriendRequest(@PathVariable Long id){
    	logger.info("Recived Request to rejectFriend request with id : {}",id);
    	FriendshipResponseDTO rejectRequest = friendshipService.rejectFriendRequest(id);
    	return new ResponseEntity<>(rejectRequest, HttpStatus.OK);
    }
    
    // Build View Friend Request REST API
    @GetMapping("/requests")
    public ResponseEntity<List<FriendshipResponseDTO>> viewFriendRequest(@RequestParam Long receiverId){
    	logger.info("Recived Request to Show Pending Request with id : {}",receiverId);
    	List<FriendshipResponseDTO> friendRequest = friendshipService.viewFriendRequests(receiverId);
    	return new ResponseEntity<>(friendRequest,HttpStatus.OK);
    }
    
    // Build All Friend List REST API
    @GetMapping("/requestss")
    public ResponseEntity<List<FriendshipResponseDTO>> listFriends(@RequestParam Long userId){
    	logger.info("Recived Request to get All friends in given id:userId");
    	List<FriendshipResponseDTO> friends = friendshipService.listFriends(userId);
    	return new ResponseEntity<>(friends,HttpStatus.OK);
    }
    
    // Build Remove Friend REST API
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFriend(@PathVariable Long id) {
    	logger.info("Recived  Request to delete friend with id : {}",id);
        friendshipService.removeFriend(id);
       // return ResponseEntity.noContent().build();
        return new ResponseEntity<>("Friend Successfully Deleted",HttpStatus.OK);
    }
}
