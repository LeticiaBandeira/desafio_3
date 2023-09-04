package com.lmbl.desafio.Controller;

import com.lmbl.desafio.Comment.Comment;
import com.lmbl.desafio.Comment.CommentRecordDTO;
import com.lmbl.desafio.Repository.CommentRepository;
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
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentRepository.findAll());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Object> getOneComment(@PathVariable(value = "id") Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment wasn't found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comment.get());
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable(value = "id") Long id, @RequestBody @Valid CommentRecordDTO commentRecordDTO) {
        Optional<Comment> commentResponse = commentRepository.findById(id);
        if (commentResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment wasn't found.");
        }
        Comment comment = commentResponse.get();
        BeanUtils.copyProperties(commentRecordDTO, comment);
        return ResponseEntity.status(HttpStatus.OK).body(commentRepository.save(comment));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable(value = "id") Long id) {
        Optional<Comment> commentResponse = commentRepository.findById(id);
        if (commentResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment wasn't found.");
        }
        commentRepository.delete(commentResponse.get());
        return ResponseEntity.status(HttpStatus.OK).body("Comment was deleted successfully.");
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> saveComment(@RequestBody @Valid CommentRecordDTO commentRecordDTO) {
        Comment commentObj = new Comment();
        BeanUtils.copyProperties(commentRecordDTO, commentObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentRepository.save(commentObj));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "postId") Long postId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = postRepository.findById(postId).map(post -> {
            commentRequest.setPost(post);
            return commentRepository.save(commentRequest);
        }).orElseThrow();

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }



}