package com.acme.elegant.service;

import com.acme.elegant.exception.ResourceNotFoundException;
import com.acme.elegant.model.Tag;
import com.acme.elegant.repository.PostRepository;
import com.acme.elegant.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(()->new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagRequest) {
        Tag tag = getTagById(tagId);
        tag.setName(tagRequest.getName());
        return tagRepository.save(tag);
    }

    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        Tag tag = getTagById(tagId);
        tagRepository.delete(tag);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Tag> getTagsByPostId(Long postId, Pageable pageable) {
        return postRepository.findById(postId).map(post -> {
            List<Tag> tags = post.getTags();
            return new PageImpl<>(tags, pageable, tags.size());
        }).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
    }

}
