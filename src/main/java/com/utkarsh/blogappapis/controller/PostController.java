package com.utkarsh.blogappapis.controller;

import com.utkarsh.blogappapis.config.AppConstants;
import com.utkarsh.blogappapis.entity.Post;
import com.utkarsh.blogappapis.payloads.CategoryDto;
import com.utkarsh.blogappapis.payloads.PostDto;
import com.utkarsh.blogappapis.payloads.PostResponse;
import com.utkarsh.blogappapis.response.ApiResponse;
import com.utkarsh.blogappapis.services.FileService;
import com.utkarsh.blogappapis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts/add")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Long userId,@PathVariable("categoryId") Long categoryId){
        PostDto createdPostDto= postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> fetchAllPost(
            @RequestParam(value ="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        return ResponseEntity.ok(postService.getAllPost(pageNumber,pageSize,sortBy,sortDir));
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> fetchPostByUser(@PathVariable("userId") Long userId,
                                                         @RequestParam(value ="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                         @RequestParam(value ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                        @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                        @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)  {
        PostResponse postResponse = postService.getPostByUser(userId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> fetchPostByCategory(@PathVariable("categoryId") Long categoryId,
                                                             @RequestParam(value ="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                             @RequestParam(value ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue =  AppConstants.SORT_BY,required = false) String sortBy,
                                                            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)  {
        PostResponse postResponse = postService.getPostByCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> fetchPostById(@PathVariable("postId") Long postId)  {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @PathVariable("postId")Long postId, @RequestBody PostDto postDto){
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/posts/search/title")
    public ResponseEntity<List<PostDto>> searchPostTitle(@RequestParam(value = "keyword",required = false) String keyword) {
       return ResponseEntity.ok(postService.searchPostByTitle(keyword));
    }

    @GetMapping("/posts/search/content")
    public ResponseEntity<List<PostDto>> searchPostContent(@RequestParam(value = "keyword",required = false) String keyword) {
        return ResponseEntity.ok(postService.searchPostByContent(keyword));
    }

    @PostMapping("/post/{postId}/image/upload")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
                                                   @PathVariable(value = "postId")Long postId ) throws IOException {
        PostDto getPost = postService.getPostById(postId);
        String fileUploaded = fileService.uploadImage(path,image);
        getPost.setImageName(fileUploaded);
        PostDto updatePostDto = postService.updatePost(getPost,postId);
        return  new ResponseEntity<>(updatePostDto,HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getPostImage(@PathVariable("imageName") String imageName,
                                                      HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getImage(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }

}
