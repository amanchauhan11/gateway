package com.coviam.gateway.repository;

import com.coviam.gateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String username);
}
