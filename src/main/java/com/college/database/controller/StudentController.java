package com.college.database.controller;

import com.college.database.dto.StudentResponse;
import com.college.database.entity.Student;
import com.college.database.repository.StudentRepo;
import com.college.database.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepo studentRepo;
    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public String student(@RequestBody Student student){
        studentRepo.save(student);
        return "ok";
    }
    @Autowired
    StudentService studentService;
    @GetMapping("/search")
    public Page<Student> searchStudents(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return studentService.searchStudents(search, pageable);
    }
    @GetMapping("/searchstudent/{roll}")
    public StudentResponse response(@PathVariable String roll){
        Student student = studentRepo.findByRollNumber(roll);

        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        return new StudentResponse(
                student.getName(),
                student.getRollNumber(),
                student.getClassName(),
                student.getEmail()
        );



    }

    @GetMapping("/all")
    public List<Student> allStudents() {
        return studentRepo.findAll();
    }
}
