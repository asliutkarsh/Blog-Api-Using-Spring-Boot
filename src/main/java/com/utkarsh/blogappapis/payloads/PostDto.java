package com.utkarsh.blogappapis.payloads;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.utkarsh.blogappapis.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long postId;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private List<CommentDto> comments = new ArrayList<>();

}
