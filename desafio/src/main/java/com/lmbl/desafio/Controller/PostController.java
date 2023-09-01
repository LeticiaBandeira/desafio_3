package com.lmbl.desafio.Controller;

import com.lmbl.desafio.Post.Post;
import com.lmbl.desafio.Post.PostRecordDTO;
import com.lmbl.desafio.Repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Object> getOnePost(@PathVariable(value = "id") Long id){
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post wasn't found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(post.get());
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") Long id, @RequestBody @Valid PostRecordDTO postRecordDTO){
        Optional<Post> postResponse = postRepository.findById(id);
        if (postResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post wasn't found.");
        }
        Post post = postResponse.get();
        BeanUtils.copyProperties(postRecordDTO, post);
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(post));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Long id){
        Optional<Post> postResponse = postRepository.findById(id);
        if (postResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post wasn't found.");
        }
        postRepository.delete(postResponse.get());
        return ResponseEntity.status(HttpStatus.OK).body("post was deleted successfully.");
    }


    @PostMapping("/posts")
    public ResponseEntity<Post> savePost(@RequestBody @Valid PostRecordDTO postRecordDTO){
        Post postObj = new Post();
        BeanUtils.copyProperties(postRecordDTO, postObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(postObj));
    }

}
