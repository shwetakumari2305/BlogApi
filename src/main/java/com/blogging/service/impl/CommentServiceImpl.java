package com.blogging.service.impl;

import com.blogging.entity.Comment;
import com.blogging.entity.Post;
import com.blogging.exception.ResourceNotFoundException;
import com.blogging.payload.CommentDto;
import com.blogging.repository.CommentRepository;
import com.blogging.repository.PostRepository;
import com.blogging.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment created = commentRepository.save(comment);
        return mapToDto(created);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );
        List<Comment> listComments = commentRepository.findByPostId(postId);
        List<CommentDto> comments = listComments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return comments;
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId)
        );

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        Comment updated = commentRepository.save(comment);

        return mapToDto(updated);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId)
        );

        commentRepository.delete(comment);
    }

    private Comment mapToEntity(CommentDto commentDto){
//        Comment comment = new Comment();
        Comment comment = modelMapper.map(commentDto, Comment.class);

//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }

    private CommentDto mapToDto(Comment comment){
//        CommentDto commentDto = new CommentDto();
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
        return commentDto;
    }
}
