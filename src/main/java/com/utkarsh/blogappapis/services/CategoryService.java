package com.utkarsh.blogappapis.services;

import com.utkarsh.blogappapis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    public List<CategoryDto> getAllCategory();
    public CategoryDto getCategoryById(Long categoryId);
    public void deleteCategory(Long category);
}
