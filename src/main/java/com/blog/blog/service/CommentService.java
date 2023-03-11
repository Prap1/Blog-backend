package com.blog.blog.service;

import org.springframework.stereotype.Service;

import com.blog.blog.payload.CommentDto;

@Service
public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
