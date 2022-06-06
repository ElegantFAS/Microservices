package com.acme.elegant.service;

import com.acme.elegant.exception.ResourceNotFoundException;
import com.acme.elegant.model.User;
import com.acme.elegant.repository.PostRepository;
import com.acme.elegant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = getUserById(userId);
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<User> getUsersByPostLikedId(Long postLikedId, Pageable pageable) {

        return postRepository.findById(postLikedId).map(post->{
            List<User> users = post.getUsersLiked();
            return new PageImpl<>(users, pageable, users.size());
        }).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postLikedId));
    }

    @Override
    public User assignFollower(Long followerId, Long followedId) {

        User follower = userRepository.findById(followerId)
                .orElseThrow(()->new ResourceNotFoundException("UserFollower", "Id", followerId));

        return userRepository.findById((followedId)).map(followed->{
            if(!followed.getFollowers().contains(follower)){
                followed.getFollowers().add(follower);
                return userRepository.save(followed);
            }
            return followed;
        }).orElseThrow(()->new ResourceNotFoundException("UserFollowed", "Id", followedId));

    }

    @Override
    public Page<User> getFollowers(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user->{
            List<User> users = user.getFollowers();
            return new PageImpl<>(users, pageable, users.size());
        }).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<User> getFollowed(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user->{
            List<User> users = user.getFollowed();
            return new PageImpl<>(users, pageable, users.size());
        }).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
    }
}
