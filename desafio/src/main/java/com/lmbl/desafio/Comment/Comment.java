package com.lmbl.desafio.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

    @Table
    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public class Comment {

        @Id
        @Column(name = "id_comment")
        private Long id;
        private String body;



    }
