package com.college.database.repository;

import com.college.database.entity.Teacher_subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Teacher_subRepo extends JpaRepository<Teacher_subject,Long> {
}
