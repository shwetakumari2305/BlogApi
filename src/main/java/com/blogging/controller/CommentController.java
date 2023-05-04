package com.blogging.controller;

import com.blogging.payload.CommentDto;
import com.blogging.service.CommentService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createNewComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("postId") long postId
    ){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(
            @PathVariable("postId") long postId
    ){
       return commentService.getAllCommentsByPostId(postId);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId,
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.updateComment(postId,commentId,commentDto),HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
     ){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
