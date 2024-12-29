package com.facebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.facebook.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUserId(Long userId);
	
	
    // Find by product name (case-insensitive)
  // List<Post> findByNameContainingIgnoreCase(String content);
    
    // Find by product name (case-insensitive)
    @Query("SELECT p FROM Post p WHERE p.content LIKE %:content%")
    List<Post> searchPostByContent(@Param("content") String content);
}
