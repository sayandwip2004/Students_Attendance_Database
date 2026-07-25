package com.college.database.service;

import com.college.database.entity.Student;
import com.college.database.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepository;

    public Page<Student> searchStudents(String search, Pageable pageable) {

        if (search == null || search.isBlank()) {
            return studentRepository.findAll(pageable);
        }

        return studentRepository.findByNameContainingIgnoreCaseOrRollNumberContainingIgnoreCase(
                search,
                search,
                pageable
        );
    }
}
