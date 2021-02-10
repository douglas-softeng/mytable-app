package com.douglas.softeng.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.douglas.softeng.model.User;
import com.douglas.softeng.service.UserService;

@Controller
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "index";
    }
    
    @GetMapping("/adduser-form")
    public String showAddUserForm(User user) {
        return "add-user";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "update-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userService.createUser(user);
        return "redirect:/";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        
        userService.updateUser(id, user);
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
