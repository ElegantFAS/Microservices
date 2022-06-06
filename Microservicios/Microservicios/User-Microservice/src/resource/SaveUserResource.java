package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveUserResource {

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;
}
