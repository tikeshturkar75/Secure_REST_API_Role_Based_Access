package com.mindprove.midprove.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mindprove.midprove.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
