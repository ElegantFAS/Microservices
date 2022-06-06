package com.acme.elegant.controller;

import com.acme.elegant.model.Image;
import com.acme.elegant.resource.ImageResource;
import com.acme.elegant.resource.SaveImageResource;
import com.acme.elegant.service.ImageService;
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

@Tag(name="images")
@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelMapper mapper;

    private Image convertToEntity(SaveImageResource resource){return mapper.map(resource, Image.class); }
    private ImageResource convertToResource(Image entity){return mapper.map(entity, ImageResource.class); }

    @PostMapping("/posts/{postId}/images")
    public ImageResource createImage(@PathVariable(name="postId") Long postId,
                                     @Valid @RequestBody SaveImageResource resource){
        return convertToResource(imageService.createImage(postId, convertToEntity(resource)));
    }

    @PutMapping("/images/{imageId}")
    public ImageResource updateImage(@PathVariable(name="imageId") Long imageId,
                                     @Valid @RequestBody SaveImageResource resource){
        return convertToResource(imageService.updateImage(imageId, convertToEntity(resource)));
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable(name="imageId") Long imageId){
        return imageService.deleteImage(imageId);
    }

    @GetMapping("/posts/{postId}/images")
    public Page<ImageResource> getImagesByPostId(@PathVariable(name="postId") Long postId,
                                                 Pageable pageable){
        Page<Image> images = imageService.getImagesByPostId(postId, pageable);
        List<ImageResource> resources = images.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/images/{imageId}")
    public ImageResource getImageById(@PathVariable(name="imageId") Long imageId){
        return convertToResource(imageService.getImageById(imageId));
    }
}
