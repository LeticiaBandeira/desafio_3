package com.lmbl.desafio.Repository;

import com.lmbl.desafio.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
