package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Clob;

@Getter
@Setter
public class SaveImageResource {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String extension;
    @NotNull
    @NotBlank
    private String content;
}
