package com.acme.elegant.service;

import com.acme.elegant.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    Image createImage(Long postId, Image image);
    Image getImageById(Long imageId);
    Page<Image> getImagesByPostId(Long postId, Pageable pageable);
    Image updateImage(Long imageId, Image imageRequest);
    ResponseEntity<?> deleteImage(Long imageId);
}
