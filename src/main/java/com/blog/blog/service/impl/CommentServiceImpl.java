package com.blog.blog.service.impl;

import com.blog.blog.entity.Comment;
import com.blog.blog.entity.Post;
import com.blog.blog.exceptions.ResourceNotFound;
import com.blog.blog.payload.CommentDto;
import com.blog.blog.repository.CommentRepo;
import com.blog.blog.repository.PostRepo;
import com.blog.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post"," post Id ",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saveComment = this.commentRepo.save(comment);
        return this.modelMapper.map(saveComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("Comment", " Comment Id ", commentId));
        this.commentRepo.delete(comment);
    }
}
