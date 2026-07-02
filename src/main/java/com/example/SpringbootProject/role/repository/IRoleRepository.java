package com.example.SpringbootProject.role.repository;

import com.example.SpringbootProject.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
