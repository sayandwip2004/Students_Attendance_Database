package com.college.database.controller;

import com.college.database.entity.Attendance;
import com.college.database.repository.AttendanceRepo;
import com.college.database.util.JwtUtil;
import jakarta.validation.constraints.Null;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceRepo attendanceRepo;
    @Autowired
    JwtUtil jwtUti;
    @PostMapping("/add")
    @PreAuthorize("hasRole('TEACHER')")
    public  Boolean attendance(@RequestBody Attendance attendance){
//        if(authHeader==null || !authHeader.startsWith("Bearer")){
//            return false;
//        }
//        String token=authHeader.substring(7);
//        if(jwtUti.isTokenValid(token)){
            attendanceRepo.save(attendance);
            return true;
//
//        }
//
//        return false;
//
    }

}
