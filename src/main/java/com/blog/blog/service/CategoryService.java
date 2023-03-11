package com.blog.blog.service;


import org.springframework.stereotype.Service;

import com.blog.blog.payload.CategoryDto;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getCategories();
}
