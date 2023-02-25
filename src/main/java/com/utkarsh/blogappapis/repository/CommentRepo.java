package com.utkarsh.blogappapis.repository;

import com.utkarsh.blogappapis.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
