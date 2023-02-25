package com.utkarsh.blogappapis.repository;

import com.utkarsh.blogappapis.entity.Category;
import com.utkarsh.blogappapis.entity.Post;
import com.utkarsh.blogappapis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    Page<Post> findPostsByUser(User user,Pageable pageable);

    Page<Post> findPostsByCategory(Category category,Pageable pageable);

    List<Post> findByTitleContaining(String keyword);

    List<Post> findByContentContaining(String keyword);
}
