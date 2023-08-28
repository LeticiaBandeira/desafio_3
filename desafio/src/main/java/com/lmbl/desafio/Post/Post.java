package com.lmbl.desafio.Post;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Post {

    @Id
    @Column(name = "id_post")
    private Long id;

    private String title;
    private String body;



}
