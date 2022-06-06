package com.acme.elegant.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="profiles")
@Getter
@Setter
public class Profile extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @NotNull
    @NotBlank
    private String firstNames;

    @NotNull
    @NotBlank
    private String lastNames;

    @NotNull
    @NotBlank
    private int cellphone;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String size;

}
