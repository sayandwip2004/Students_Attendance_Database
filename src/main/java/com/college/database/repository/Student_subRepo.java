package com.college.database.repository;

import com.college.database.entity.Student_subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Student_subRepo extends JpaRepository<Student_subject,Long> {
}
