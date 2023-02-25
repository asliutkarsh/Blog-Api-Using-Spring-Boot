package com.utkarsh.blogappapis.services;

import com.utkarsh.blogappapis.entity.Category;
import com.utkarsh.blogappapis.entity.Post;
import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.payloads.PostDto;
import com.utkarsh.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Long userId,Long categoryId);

    PostDto updatePost(PostDto postDto,Long postId);

    void deletePost(Long postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Long postId);

    PostResponse getPostByUser(Long userId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostResponse getPostByCategory(Long categoryId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    List<PostDto> searchPostByTitle(String keyword);

    List<PostDto> searchPostByContent(String keyword);



}
