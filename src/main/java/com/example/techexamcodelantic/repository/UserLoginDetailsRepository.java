package com.example.techexamcodelantic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techexamcodelantic.models.User;
import com.example.techexamcodelantic.models.UserLogin;

@Repository
public interface UserLoginDetailsRepository extends JpaRepository<UserLogin, Long> {
 
    List<UserLogin> findByUser(User user);
}