package com.acme.elegant.controller;

import com.acme.elegant.model.User;
import com.acme.elegant.resource.SaveUserResource;
import com.acme.elegant.resource.UserResource;
import com.acme.elegant.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="users")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity){
        return mapper.map(entity, UserResource.class);
    }

    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> users = userService.getAllUsers(pageable);
        List<UserResource> resources = users.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));

    }

    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable(name = "userId") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/posts/{postLikedId}/likes")
    public Page<UserResource> getUsersByPostLikedId(@PathVariable(name="postLikedId") Long postLikedId,
                                                    Pageable pageable){
        Page<User> users = userService.getUsersByPostLikedId(postLikedId, pageable);
        List<UserResource> resources = users.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users/{followerId}/follows/{followedId}")
    public UserResource assignFollower(@PathVariable(name="followerId") Long followerId,
                                       @PathVariable(name="followedId") Long followedId){

        return convertToResource(userService.assignFollower(followerId, followedId));
    }

    @GetMapping("/users/{userId}/followers")
    public Page<UserResource> getFollowers(@PathVariable(name="userId") Long userId,
                                           Pageable pageable){
        Page<User> users = userService.getFollowers(userId, pageable);
        List<UserResource> resources = users.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}/followed")
    public Page<UserResource> getFollowed(@PathVariable(name="userId") Long userId,
                                           Pageable pageable){
        Page<User> users = userService.getFollowed(userId, pageable);
        List<UserResource> resources = users.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
}
