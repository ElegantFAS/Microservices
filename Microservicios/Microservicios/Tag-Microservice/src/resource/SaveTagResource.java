package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveTagResource {

    @NotNull
    @NotBlank
    private String name;
}
