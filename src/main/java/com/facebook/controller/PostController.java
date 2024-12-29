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


import com.facebook.dto.PostRequestDTO;
import com.facebook.dto.PostResponseDTO;
import com.facebook.service.PostService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	PostService postService;
	
	// Build Create Post REST API
	@PostMapping("/create")
	public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO){
		logger.info("Recived Request to Create Post with id: {}",postRequestDTO.getUserId());
		PostResponseDTO post = postService.createPost(postRequestDTO);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	// Build Get Post REST API
	@GetMapping("/getPost/{id}")
	public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id){
		logger.info("Recived Request to Get Post With id :: {}",id);
		PostResponseDTO post = postService.getPost(id);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	// Build Get All Post REST API
	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostResponseDTO>> getAllPost(){
		logger.info("Recived Request to get All Post");
		List<PostResponseDTO> posts = postService.getAllPost();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	// Build update Post REST  API
	@PutMapping("/update/{id}")
	public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @RequestBody PostRequestDTO postRequestDTO){
		logger.info("Recived Request to Update Post with Name:: {}",postRequestDTO.getUserId());
		PostResponseDTO updatedPost = postService.updatePost(id, postRequestDTO);
		return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}
	
    // Build Delete Post REST API
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id){
		logger.info("Recived Request to Delete Post With Id :: {}",id);
		postService.deletePost(id);
		return new ResponseEntity<>("Post Successfully deleted", HttpStatus.OK);
	}
	
	// Build search Post REST API
	@GetMapping("/search")
	public ResponseEntity<List<PostResponseDTO>> searchPost(@RequestParam String content){
		logger.info("Recived Request to search Post By Content::{}",content);
		List<PostResponseDTO> post = postService.searchPost(content);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
}
