package com.utkarsh.blogappapis.repository;

import com.utkarsh.blogappapis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
