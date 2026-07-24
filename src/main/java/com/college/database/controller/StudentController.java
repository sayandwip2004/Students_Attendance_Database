package com.college.database.controller;

import com.college.database.entity.Student;
import com.college.database.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepo studentRepo;
    @PostMapping("/add")
    public String student(@RequestBody Student student){
        studentRepo.save(student);
        return "ok";
    }
}
