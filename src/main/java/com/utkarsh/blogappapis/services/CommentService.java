package com.utkarsh.blogappapis.services;

import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Long postId, Long userId);

    CommentDto updateComment(CommentDto commentDto,Long commentId);

    void deleteComment(Long commentId);


}
