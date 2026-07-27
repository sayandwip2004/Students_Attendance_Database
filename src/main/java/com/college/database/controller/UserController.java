package com.college.database.controller;

import com.college.database.dto.LoginRequest;
import com.college.database.entity.User;
import com.college.database.repository.UserRepo;
import com.college.database.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @PostMapping("/register")
    public String Createuser(@RequestBody User user ){
        userRepo.save(user);
        return "Registered";
    }


    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request ){
        User user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            return  "User not found";
        }
        if (!request.getPassword().equals(user.getPassword())) {
            return  "Wrong password";
        }
        return jwtUtil.generateToken(user.getEmail());
    }



    @GetMapping("/find/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean Details(@PathVariable String email,@RequestHeader("Authorization") String authHeader  ){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }
        String token = authHeader.substring(7);//expect bearer
        if(jwtUtil.isTokenValid(token)){
            return userRepo.existsByEmail(email);
        }

        return false;

    }

}
