package com.college.database.controller;

import com.college.database.entity.User;
import com.college.database.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @PostMapping("/createuser")
    public String Createuser(@RequestBody User user ){
        userRepo.save(user);
        return "Registered";
    }

    @GetMapping("/find/{email}")
    public Boolean Details(@PathVariable String email  ){
        return userRepo.existsByEmail(email);


    }

}
