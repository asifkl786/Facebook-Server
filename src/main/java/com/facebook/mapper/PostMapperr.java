package com.facebook.mapper;



import com.facebook.dto.PostRequestDTO;
import com.facebook.dto.PostResponseDTO;
import com.facebook.entity.Post;
import com.facebook.entity.User;

import java.text.SimpleDateFormat;

public class PostMapperr {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Map PostRequestDTO to Post Entity
    public static Post toEntity(PostRequestDTO dto, User user) {
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setImageUrl(dto.getImageUrl());
        post.setUser(user); // Set User reference
        return post;
    }

    // Map Post Entity to PostResponseDTO
    public static PostResponseDTO toDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
       // dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setUserFullName(post.getUser().getFirstName() + " " + post.getUser().getLastName());// Custom field
        dto.setCreatedAt(dateFormat.format(post.getCreatedAt()));
        dto.setUpdatedAt(dateFormat.format(post.getUpdatedAt()));
        return dto;
    }
}

