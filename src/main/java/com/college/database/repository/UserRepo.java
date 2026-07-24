package com.college.database.repository;

import com.college.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long>{
    User findByEmail(String email);
    boolean existsByEmail(String email);

}
