package com.lmbl.desafio.Repository;

import com.lmbl.desafio.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
}
