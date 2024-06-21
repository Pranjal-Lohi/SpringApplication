package com.pranjal.SpringApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranjal.SpringApplication.models.ApplicationUser;
import com.pranjal.SpringApplication.models.Comment;
import com.pranjal.SpringApplication.models.Post;
import com.pranjal.SpringApplication.repositories.CommentRepo;

    @Service
    public class CommentService {
        @Autowired
        private CommentRepo commentRepo;
        
        public List<Comment> getCommentsByPost(Post post) {
            return commentRepo.findByPost(post);
        }
        
        public void addComment(Post post, ApplicationUser user, String text) {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(text);
            commentRepo.save(comment);
        }
    }