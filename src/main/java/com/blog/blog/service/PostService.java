package com.blog.blog.service;

import com.blog.blog.entity.Post;
import com.blog.blog.payload.PostDto;
import com.blog.blog.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto CreatePost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatedPost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String Keyword);
}
