package com.example.SpringbootProject.user.repository;

import com.example.SpringbootProject.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
