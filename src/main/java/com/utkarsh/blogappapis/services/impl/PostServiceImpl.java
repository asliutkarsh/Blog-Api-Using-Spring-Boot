package com.utkarsh.blogappapis.services.impl;

import com.utkarsh.blogappapis.entity.Category;
import com.utkarsh.blogappapis.entity.Post;
import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.exception.ResourceNotFoundException;
import com.utkarsh.blogappapis.payloads.PostDto;
import com.utkarsh.blogappapis.payloads.PostResponse;
import com.utkarsh.blogappapis.repository.CategoryRepo;
import com.utkarsh.blogappapis.repository.PostRepo;
import com.utkarsh.blogappapis.repository.UserRepo;
import com.utkarsh.blogappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepo.save(post);
        return modelMapper.map(savedPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage =  postRepo.findAll(p);
        List<Post> postList =postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map((post -> modelMapper.map(post, PostDto.class))).toList();
        return new PostResponse(
                postDtoList,postPage.getNumber(),postPage.getSize(),postPage.getNumberOfElements(),postPage.getTotalPages(),postPage.isLast());
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getPostByUser(Long userId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage =  postRepo.findPostsByUser(user,p);
        List<Post> postList =postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map((post -> modelMapper.map(post, PostDto.class))).toList();
        return new PostResponse(
                postDtoList,postPage.getNumber(),postPage.getSize(),postPage.getNumberOfElements(),postPage.getTotalPages(),postPage.isLast());
    }

    @Override
    public PostResponse getPostByCategory(Long categoryId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage =  postRepo.findPostsByCategory(category,p);
        List<Post> postList =postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map((post -> modelMapper.map(post, PostDto.class))).toList();
        return new PostResponse(
                postDtoList,postPage.getNumber(),postPage.getSize(),postPage.getNumberOfElements(),postPage.getTotalPages(),postPage.isLast());
    }

    @Override
    public List<PostDto> searchPostByTitle(String keyword) {
        List<Post> postList = postRepo.findByTitleContaining(keyword);
        return postList.stream().map((post -> modelMapper.map(post,PostDto.class))).toList();
    }

    @Override
    public List<PostDto> searchPostByContent(String keyword) {
        List<Post> postList = postRepo.findByContentContaining(keyword);
        return postList.stream().map((post -> modelMapper.map(post,PostDto.class))).toList();
    }


}
