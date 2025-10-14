package com.example.user_service.repository;

import com.example.user_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByNameContaining(String name);
}
