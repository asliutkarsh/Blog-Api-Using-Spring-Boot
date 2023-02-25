package com.utkarsh.blogappapis.services.impl;

import com.utkarsh.blogappapis.entity.Comment;
import com.utkarsh.blogappapis.entity.Post;
import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.exception.ResourceNotFoundException;
import com.utkarsh.blogappapis.payloads.CommentDto;
import com.utkarsh.blogappapis.repository.CommentRepo;
import com.utkarsh.blogappapis.repository.PostRepo;
import com.utkarsh.blogappapis.repository.UserRepo;
import com.utkarsh.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        commentRepo.delete(comment);

    }
}
