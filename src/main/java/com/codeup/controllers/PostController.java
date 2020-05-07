package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostRepo;
import com.codeup.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {


    private UserRepo userDao;
    private PostRepo postDao;


    public PostController(){}

    public PostController(UserRepo userDao, PostRepo postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String getPost(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "views/posts";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post",postDao.getOne(id));
        return "views/individualPost";
    }

    @PostMapping("posts/delete/{id}")
    public String updatePost(@PathVariable long id) {
        System.out.println((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.getId() == postDao.getOne(id).getUser().getId()){
            // delete post
            System.out.println(loggedInUser.getId());
            System.out.println(postDao.getOne(id).getUser().getId());
            System.out.println(postDao.getOne(id).getId());
            postDao.deleteById(id);
        }else{
            return "redirect:/posts";
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post post, @PathVariable long id) {
        Post p = postDao.getOne(id);
        p.setTitle(p.getTitle());
        p.setBody(p.getBody());
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "views/createPost";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        post.setUser(userDao.getOne(1L));
        postDao.save(post);
        return "redirect:/posts";
    }
}
