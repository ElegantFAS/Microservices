package com.acme.elegant.service;

import com.acme.elegant.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;



public interface TagService {
    Tag createTag(Tag tag);
    Tag getTagById(Long tagId);
    Page<Tag> getAllTags(Pageable pageable);
    Tag updateTag(Long tagId, Tag tagRequest);
    ResponseEntity<?> deleteTag(Long tagId);

    Page<Tag> getTagsByPostId(Long postId, Pageable pageable);
}
