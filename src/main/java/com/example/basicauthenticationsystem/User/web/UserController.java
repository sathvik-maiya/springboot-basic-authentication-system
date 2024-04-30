package com.example.basicauthenticationsystem.User.web;

import com.example.basicauthenticationsystem.User.UserService;
import com.example.basicauthenticationsystem.User.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String>  createUser(@RequestBody UserModel user, HttpServletResponse res){
       return userService.postUser(user,res);
    }

    @GetMapping("/userPage")
    public ResponseEntity<String>  getUser(HttpServletRequest req){
        return userService.getUserPage(req);
    }

}
