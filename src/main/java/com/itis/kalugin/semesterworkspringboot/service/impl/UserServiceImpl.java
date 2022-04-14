package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.repository.UserRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final String DEFAULT_AVATAR = "https://res.cloudinary.com/dwzcudur6/image/upload/v1634711026/defaultUser_msfkll.png";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDto save(CreateUserDto user) {
        return UserDto.fromModel(userRepository.save(new User(user.getNickname(), user.getFirstName(),
                user.getSecondName(), DEFAULT_AVATAR, user.getEmail(),
                encoder.encode(user.getPassword()))));
    }
}
