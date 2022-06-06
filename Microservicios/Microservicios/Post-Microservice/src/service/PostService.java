package com.acme.elegant.service;

import com.acme.elegant.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostService {

    //funcionalidades para el cliente
    Post createPost(Long userId, Post post);
    Post updatePost(Long postId, Post postRequest);
    ResponseEntity<?> deletePost(Long postId);

    //obtener todos los posts para el feed
    Page<Post> getAllPosts(Pageable pageable);

    //obtener solo 1 post al hacer click
    Post getPostById(Long postId);


    //posts de tienda (sellable=true) posts de galeria (sellable=false)
    Page<Post> getPostsBySellable(boolean sellable, Pageable pageable);

    //obtener solo los posts de 1 persona
    Page<Post> getPostsByUserId(Long userId, Pageable pageable);

    Post assignLike(Long userId, Long postId);
    Page<Post> getPostsByUserLikedId(Long userLikedId, Pageable pageable);

    Post assignTag(Long postId, Long tagId);
    Page<Post> getPostsByTagId(Long tagId, Pageable pageable);
}
