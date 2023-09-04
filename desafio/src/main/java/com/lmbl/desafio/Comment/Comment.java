package com.lmbl.desafio.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lmbl.desafio.Post.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Table
    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_comment")
        private Long id;
        private String body;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "fk_post", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
    private Post post;

    }
