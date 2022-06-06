package com.acme.elegant.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Clob;

@Entity
@Table(name="images")
@Getter
@Setter
public class Image extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Post post;

    /*@Id
    @Column(name="imageable_id")
    private Long imageableId;

    @Id
    @Column(name="imageable_class")
    private String imageableClass;*/

    /*@Column(length=200, nullable = false)
    private String path;*/

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 5, nullable = false)
    private String extension;

    @Column(nullable = false)
    @Lob
    private String content;


}
