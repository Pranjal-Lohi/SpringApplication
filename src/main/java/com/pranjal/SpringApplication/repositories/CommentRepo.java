package com.pranjal.SpringApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranjal.SpringApplication.models.Comment;
import com.pranjal.SpringApplication.models.Post;

    public interface CommentRepo extends JpaRepository<Comment, Long> {
        List<Comment> findByPost(Post post);
    }
