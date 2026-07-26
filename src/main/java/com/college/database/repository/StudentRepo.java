package com.college.database.repository;

import com.college.database.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long> {
    boolean existsByRollNumber(String rollNumber);

    Student findByRollNumber(String rollNumber);

    Page<Student> findByNameContainingIgnoreCaseOrRollNumberContainingIgnoreCase(
            String name,
            String rollNumber,
            Pageable pageable
    );
}
