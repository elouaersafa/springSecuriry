package com.example.springsecurity.repository;

import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
