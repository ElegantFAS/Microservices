package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePostResource {
    @NotNull
    @NotBlank
    private String description;
    private boolean sellable = false;
    private double price = 0;
    private int stock = 1;
    @NotNull
    @NotBlank
    private String size;

}
