package com.example.basicauthenticationsystem.User;

import com.example.basicauthenticationsystem.User.model.UserModel;
import com.example.basicauthenticationsystem.User.model.UserRepository;
import com.example.basicauthenticationsystem.Utils.JWTUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    public ResponseEntity<String>  postUser(UserModel user, HttpServletResponse res){
        userRepository.save(user);
        String token= jwtUtils.generateToken(user.getName());
        addTokenToCookie(res, token);
        return ResponseEntity.ok("logged in successfully");
    }
    private void addTokenToCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("auth_token", token);
        cookie.setMaxAge(360000);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public ResponseEntity<String>  getUserPage(HttpServletRequest req){
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("auth_token")){
                    String authToken = cookie.getValue();
                    String userName=jwtUtils.extractUserName(authToken);
                    UserModel user = userRepository.findByName(userName);
                    if (user != null) {
                        return ResponseEntity.ok("Welcome to user page");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User account not found");
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");

    }




}
