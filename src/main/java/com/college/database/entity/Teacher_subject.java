package com.college.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_student")
public class Teacher_subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Teacher user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_email", referencedColumnName = "email", nullable = false)
    private User teacher;

    // Subject
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_code", referencedColumnName = "code",nullable = false
    )
    private Subject subject;
}
