package com.lmbl.desafio.Post;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lmbl.desafio.Comment.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

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
