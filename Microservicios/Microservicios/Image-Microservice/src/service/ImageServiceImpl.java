package com.acme.elegant.service;

import com.acme.elegant.exception.ResourceNotFoundException;
import com.acme.elegant.model.Image;
import com.acme.elegant.repository.ImageRepository;
import com.acme.elegant.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Image createImage(Long postId, Image image) {
        return postRepository.findById(postId).map(post->{
            image.setPost(post);
            return imageRepository.save(image);
        }).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
    }

    @Override
    public Image getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(()->new ResourceNotFoundException("Image","Id", imageId));
    }

    @Override
    public Page<Image> getImagesByPostId(Long postId, Pageable pageable) {
        return imageRepository.findByPostId(postId, pageable);
    }

    @Override
    public Image updateImage(Long imageId, Image imageRequest) {
        Image image = getImageById(imageId);
        image.setContent(imageRequest.getContent());
        image.setName(imageRequest.getName());
        image.setExtension(imageRequest.getExtension());
        return imageRepository.save(image);
    }

    @Override
    public ResponseEntity<?> deleteImage(Long imageId) {
        Image image = getImageById(imageId);
        imageRepository.delete(image);
        return ResponseEntity.ok().build();
    }
}
