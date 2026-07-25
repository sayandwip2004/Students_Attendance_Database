package com.college.database.controller;

import com.college.database.entity.Attendance;
import com.college.database.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceRepo attendanceRepo;
    @PostMapping("/add")
//    @PreAuthorize("hasRole('TEACHER')")
    public  String attendance(@RequestBody Attendance attendance){
        attendanceRepo.save(attendance);
        return "ok";

    }
}
