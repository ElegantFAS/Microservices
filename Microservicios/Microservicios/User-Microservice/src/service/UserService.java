package com.acme.elegant.service;

import com.acme.elegant.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User createUser(User user);
    User getUserById(Long userId);
    Page<User> getAllUsers(Pageable pageable);
    User updateUser(Long userId, User userRequest);
    ResponseEntity<?> deleteUser(Long userId);

    Page<User> getUsersByPostLikedId(Long postLikedId, Pageable pageable);

    User assignFollower(Long followerId, Long followedId);
    Page<User> getFollowers(Long userId, Pageable pageable);
    Page<User> getFollowed(Long userId, Pageable pageable);
}
