package com.college.database.controller;

import com.college.database.entity.Subject;
import com.college.database.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectRepo subjectRepo;
    @PostMapping("/add")
    public String subject(@RequestBody Subject subject){
        subjectRepo.save(subject);
        return "ok";
    }

}
