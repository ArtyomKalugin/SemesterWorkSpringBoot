package com.itis.kalugin.semesterworkspringboot.repository;

import com.itis.kalugin.semesterworkspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email);
}
