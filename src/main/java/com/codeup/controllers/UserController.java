package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.repositories.UserRepo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserRepo usersDao;

    public UserController(UserRepo usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "views/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        user.setProfileImageUrl("https://picsum.photos/200");
        usersDao.save(user);
        return "redirect:/login";
    }

//    @GetMapping("/profile")
//    public String profile(Model model) {
//        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", u);
//        model.addAttribute("recipes", recipeDao.findAll());
//        model.addAttribute("fsapi", fsapi);
//        if (u.isAdmin()){
//            System.out.println("this user is an admin!");
//        }
//        System.out.println("inside profile method!");
//        return "recipes/profile";
//    }

//    @GetMapping("/profile/{id}")
//    public String viewUserProfile(Model model, @PathVariable long id) {
//        User u = usersDao.getOne(id);
//        model.addAttribute("user", u);
//        model.addAttribute("recipes", recipeDao.findAll());
//
//        return "recipes/profileFromRecipe";
//    }


    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        User u = usersDao.getOne(id);
        model.addAttribute("user", u);
        return "users/editProfile";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute User user, @PathVariable long id, Model model) {
        User updatedUser = usersDao.getOne(id);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFirst_name(user.getFirst_name());
        updatedUser.setLast_name(user.getLast_name());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setProfileImageUrl("https://picsum.photos/200");
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", admin);
        updatedUser.setPassword(updatedUser.getPassword());
        usersDao.save(updatedUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(updatedUser, updatedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/profile";
    }

}

