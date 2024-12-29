package com.facebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facebook.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
 // This is custom search method JPQL QUERY
    @Query("SELECT p FROM User p WHERE " +
           "p.firstName LIKE CONCAT('%', :query, '%')" +
            "Or p.lastName LIKE CONCAT('%', :query, '%')" +
            "Or p.email LIKE CONCAT('%', :query, '%')" +
            "Or p.password LIKE CONCAT('%', :query, '%')" )
    List<User> searchUserProfile(String query);
    
    Optional<User> findById(Long id);

}

