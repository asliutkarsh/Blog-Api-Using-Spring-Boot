package com.utkarsh.blogappapis.controller;

import com.utkarsh.blogappapis.payloads.CommentDto;
import com.utkarsh.blogappapis.response.ApiResponse;
import com.utkarsh.blogappapis.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comments/add")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto,
                                                 @PathVariable("postId") Long postId,
                                                 @PathVariable("userId") Long userId){
        CommentDto createdCommentDto= commentService.createComment(commentDto,postId,userId);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
    }

}
