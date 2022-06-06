package com.acme.elegant.controller;

import com.acme.elegant.model.Post;
import com.acme.elegant.resource.PostResource;
import com.acme.elegant.resource.SavePostResource;
import com.acme.elegant.service.PostService;
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

@Tag(name="posts")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper mapper;

    private Post convertToEntity(SavePostResource resource){
        return mapper.map(resource, Post.class);
    }
    private PostResource convertToResource(Post entity){
        return mapper.map(entity, PostResource.class);
    }

    @PostMapping("/users/{userId}/posts")
    public PostResource createPost(@PathVariable(name = "userId") Long userId,
                                   @Valid @RequestBody SavePostResource resource) {
        return convertToResource(postService.createPost(userId,
                convertToEntity(resource)));
    }

    @GetMapping("/feed")
    public Page<PostResource> getAllPosts(Pageable pageable) {
        Page<Post> posts = postService.getAllPosts(pageable);
        List<PostResource> resources = posts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/posts/{postId}")
    public PostResource getPostById(@PathVariable(name = "postId") Long postId) {
        return convertToResource(postService.getPostById(postId));
    }

    @PutMapping("/posts/{postId}")
    public PostResource updatePost(@PathVariable(name = "postId") Long postId,
                                   @Valid @RequestBody SavePostResource resource) {
        return convertToResource(postService.updatePost(postId, convertToEntity(resource)));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "postId") Long postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/users/{userId}/posts")
    public Page<PostResource> getPostsByUserId(@PathVariable(name = "userId") Long userId,
                                               Pageable pageable) {
        Page<Post> posts = postService.getPostsByUserId(userId, pageable);
        List<PostResource> resources = posts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/marketplace")
    public Page<PostResource> getSellablePosts(Pageable pageable) {
        Page<Post> posts = postService.getPostsBySellable(true, pageable);
        List<PostResource> resources = posts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/gallery")
    public Page<PostResource> getNonSellablePosts(Pageable pageable) {
        Page<Post> posts = postService.getPostsBySellable(false, pageable);
        List<PostResource> resources = posts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users/{userId}/posts/{postId}")
    public PostResource assignLike(@PathVariable(name="userId") Long userId,
                                   @PathVariable(name="postId") Long postId){
        return convertToResource(postService.assignLike(userId, postId));
    }

    @GetMapping("/users/{userId}/likes")
    public Page<PostResource> getPostsByUserLikedId(@PathVariable(name = "userId") Long userId,
                                                    Pageable pageable) {
        Page<Post> posts = postService.getPostsByUserLikedId(userId, pageable);
        List<PostResource> resources = posts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/posts/{postId}/tags/{tagId}")
    public PostResource assignTag(@PathVariable(name="postId") Long postId,
                                  @PathVariable(name="tagId") Long tagId){
        return convertToResource(postService.assignTag(postId, tagId));
    }

    @GetMapping("/tags/{tagId}/posts")
    public Page<PostResource> getPostsByTagId(@PathVariable(name="tagId") Long tagId,
                                              Pageable pageable){
        Page<Post> posts = postService.getPostsByTagId(tagId, pageable);
        List<PostResource> resources = posts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
}
