package com.acme.elegant.controller;


import com.acme.elegant.model.Tag;
import com.acme.elegant.resource.SaveTagResource;
import com.acme.elegant.resource.TagResource;
import com.acme.elegant.service.TagService;

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

@io.swagger.v3.oas.annotations.tags.Tag(name ="tags")
@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/tags")
    public Page<TagResource> getAllTags(Pageable pageable){
        Page<Tag> tags = tagService.getAllTags(pageable);
        List<TagResource> resources = tags.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/tags")
    public TagResource createTag(@Valid @RequestBody SaveTagResource resource){
        Tag tag = convertToEntity(resource);
        return convertToResource(tagService.createTag(tag));
    }

    @GetMapping("/tags/{tagId}")
    public TagResource getTagById(@PathVariable(name = "tagId")Long tagId){
        return convertToResource(tagService.getTagById(tagId));
    }

    @PutMapping("/tags/{tagId}")
    public TagResource updateTag(@PathVariable(name ="tagId") Long tagId, @Valid @RequestBody SaveTagResource resource){
        Tag tag = convertToEntity(resource);
        return convertToResource(tagService.updateTag(tagId, tag));
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "tagId") Long tagId){
        return tagService.deleteTag(tagId);
    }

    @GetMapping("/posts/{postId}/tags")
    public Page<TagResource> getTagsByPostId(@PathVariable(name="postId") Long postId,
                                             Pageable pageable){
        Page<Tag> tags = tagService.getTagsByPostId(postId,pageable);
        List<TagResource> resources = tags.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Tag convertToEntity(SaveTagResource resource){ return mapper.map(resource, Tag.class);}
    private TagResource convertToResource(Tag entity){return mapper.map(entity,TagResource.class);}
}
