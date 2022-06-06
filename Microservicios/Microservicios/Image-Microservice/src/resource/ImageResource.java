package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

import java.sql.Clob;

@Getter
@Setter
public class ImageResource {
    private Long id;
    private Long postId;
    private String name;
    private String extension;
    private String content;
}
