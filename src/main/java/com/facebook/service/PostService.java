package com.facebook.service;

import java.util.List;

import com.facebook.dto.PostRequestDTO;
import com.facebook.dto.PostResponseDTO;

public interface PostService {
	
	PostResponseDTO createPost(PostRequestDTO postRequestDTO);
	PostResponseDTO getPost(Long userId);
	List<PostResponseDTO> getAllPost();
	PostResponseDTO updatePost(Long userId, PostRequestDTO postRequestDTO);
	void deletePost(Long id);
	List<PostResponseDTO> searchPost(String content);

}
