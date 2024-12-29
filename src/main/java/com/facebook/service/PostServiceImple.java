package com.facebook.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facebook.dto.PostRequestDTO;
import com.facebook.dto.PostResponseDTO;
import com.facebook.entity.Post;
import com.facebook.entity.User;
import com.facebook.exception.ResourceNotFoundException;
import com.facebook.mapper.PostMapperr;
import com.facebook.repository.PostRepository;
import com.facebook.repository.UserRepository;


@Service
public class PostServiceImple implements PostService{

	private static final Logger logger = LoggerFactory.getLogger(PostServiceImple.class);
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	
	
	// Create Post
	@Override
	public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
	    // Fetch the user by ID
		User user = userRepository.findById(postRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + postRequestDTO.getUserId()));
        // Map PostRequestDTO to Post entity
        Post post = PostMapperr.toEntity(postRequestDTO, user);
        // Save the Post
        Post savedPost = postRepository.save(post);
        logger.info("Post Successfully Created With id: {}",savedPost.getId());
        // Map saved Post to PostResponseDTO and return
        return PostMapperr.toDTO(savedPost);
	}

	// Get Post By id
	@Override
	public PostResponseDTO getPost(Long userId) {
		Post post = postRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
		logger.info("{} :: Post Successfully Found ",userId);
		return PostMapperr.toDTO(post);
	}

	// Get All Post 
	@Override
	public List<PostResponseDTO> getAllPost() {
		List<Post> posts = postRepository.findAll();
		logger.info("{}:: Post Successfully found ",posts.size());
		return posts.stream().map(PostMapperr::toDTO).collect(Collectors.toList());
	}

	// Update Post By Id 
	@Override
	public PostResponseDTO updatePost(Long userId, PostRequestDTO postRequestDTO) {
		// Fetch post from database
		Post existingPost = postRepository.findById(userId)
		    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
		
		// Set Basic field
		//existingPost.setId(postRequestDTO.getUserId());
		existingPost.setContent(postRequestDTO.getContent());
		existingPost.setImageUrl(postRequestDTO.getImageUrl());
		existingPost.setUpdatedAt(new java.util.Date());
		
		// Save into data base
		Post savedPost = postRepository.save(existingPost);
		logger.info("{}:: Post Successfully updated",existingPost.getId());
		return PostMapperr.toDTO(savedPost);
	}

	// Delete Post 
	@Override
	public void deletePost(Long id) {
		Post existingPost = postRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
		postRepository.delete(existingPost);
       logger.info("{}:: Post Successfully deleted",id);		
	}
	
	// Search Post By Content
	@Override
	public List<PostResponseDTO> searchPost(String content) {
		List<Post> posts = postRepository.searchPostByContent(content);
			if (posts.isEmpty()) {
	            throw new ResourceNotFoundException("No Post found in with :{} " + content);
	        }
			//.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
			logger.info("{}:: Post Successfully Found With :",content);	
		return posts.stream().map(PostMapperr::toDTO).collect(Collectors.toList());
	}
	
}
