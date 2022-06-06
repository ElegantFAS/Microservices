package com.acme.elegant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 200, nullable = false)
    private String email;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "usersLiked")
    @JsonIgnore
    private List<Post> postsLiked;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name="followers",
            joinColumns = {@JoinColumn(name="followed_id")},//referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")})//referencedColumnName = "user_id")})
    @JsonIgnore
    private List<User> followers;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "followers")
    @JsonIgnore
    private List<User> followed;

}
