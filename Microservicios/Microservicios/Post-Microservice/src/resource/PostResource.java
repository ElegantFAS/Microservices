package com.acme.elegant.resource;

import com.acme.elegant.model.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResource {

    private Long id;
    private String description;
    private boolean sellable;
    private double price;
    private int stock;
    private String size;
    private Long userId;
    private List<ImageResource> images;

}
