package com.pranjal.SpringApplication.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pranjal.SpringApplication.models.Account;
import com.pranjal.SpringApplication.models.ApplicationUser;
import com.pranjal.SpringApplication.models.Comment;
import com.pranjal.SpringApplication.models.Post;
import com.pranjal.SpringApplication.repositories.CommentRepo;
import com.pranjal.SpringApplication.repositories.PostRepository;
import com.pranjal.SpringApplication.repositories.UserRepository;
import com.pranjal.SpringApplication.services.AccountService;
import com.pranjal.SpringApplication.services.CommentService;
import com.pranjal.SpringApplication.services.PostService;

import jakarta.validation.Valid;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepository userRepository;
    

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal) {
        Optional<Post> optionalPost = postService.getById(id);
        String authUser = "email";
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);

            //get username of current logged in session user
            //String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

            if (principal != null) {
                authUser = principal.getName();
            }
            if (authUser.equals(post.getAccount().getEmail())){
                model.addAttribute("isOwner", true);
            }else{
                model.addAttribute("isOwner", false);
            }

            return "post_views/post";
        } else {
            return "404";
        }
    }


    @GetMapping("/posts/add")
    @PreAuthorize("isAuthenticated()")
    public String addPost(Model model, Principal principal) {
        String authUser = "email";
        if(principal != null){
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_views/post_add";
        }else{
            return "redirect:/";
        }
    }
    
    @PostMapping("/posts/add")
    @PreAuthorize("isAuthenticated()")
    public String addPostHandler(@Valid @ModelAttribute Post post, BindingResult bindingResult ,Principal principal){

        if (bindingResult.hasErrors()){
            return "post_views/post_add";
        }
        String authUser = "email";
        if(principal != null){
            authUser = principal.getName();
        }
        if (post.getAccount().getEmail().compareToIgnoreCase(authUser) < 0){
            return "redirect:/?error";
        }
        postService.save(post);
        return "redirect:/posts/"+post.getId();
    }

    
    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model){
        Optional<Post> optionaPost = postService.getById(id);
        if(optionaPost.isPresent()){
            Post post = optionaPost.get();
            model.addAttribute("post", post);
            return "post_views/post_edit";
        }else{
            return "404";
        }
    }
    @PostMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@Valid @ModelAttribute Post post, BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors()){
            return "post_views/post_edit";
        }

        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost);
        }
        return "redirect:/posts/"+post.getId();

    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable Long id){
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            postService.delete(post);
            return "redirect:/";
        }else{
            return "redirect:/?error";
        }

    }

    @PostMapping("/posts/{postId}/comments")
    public String addComment(@PathVariable Long postId, @RequestBody Comment comment, Principal principal) {
        Post post = postRepository.findById(postId).orElseThrow();
        ApplicationUser user = userRepository.findByEmail(principal.getName());
        comment.setPost(post);
        comment.setUser(user);
        commentService.addComment(post, user, comment.getText());
        return "redirect:/posts/" + postId;
}

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }
    
}