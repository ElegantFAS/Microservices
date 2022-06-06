package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource {
    private Long id;
    private String email;
    private String username;
    private String password; //delete later?
}
