package com.college.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_subject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student_subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_roll", nullable = false, referencedColumnName = "roll_number")
    private Student student;

    // Subject
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_code", nullable = false,referencedColumnName = "code")
    private Subject subject;

    @Column(insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
