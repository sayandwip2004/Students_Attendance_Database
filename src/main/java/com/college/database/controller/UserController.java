package com.college.database.controller;

import com.college.database.dto.LoginRequest;
import com.college.database.entity.User;
import com.college.database.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/login")
    public LoginRequest login(@RequestBody LoginRequest request ){
        User user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if (!request.getPassword().equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

       return new LoginRequest(user.getEmail(),user.getPassword());
    }

    @GetMapping("/find/{email}")
    public Boolean Details(@PathVariable String email  ){
        return userRepo.existsByEmail(email);


    }

}
