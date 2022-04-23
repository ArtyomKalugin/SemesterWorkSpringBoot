package com.itis.kalugin.semesterworkspringboot.repository;

import com.itis.kalugin.semesterworkspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByEmail(String email);

    void deleteUserById(Integer userId);
}
