package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.helper.CloudinaryHelper;
import com.itis.kalugin.semesterworkspringboot.helper.ImageHelper;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.repository.UserRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.fromModel(userRepository.getUserByEmail(email).get());
    }

    @Override
    public UserDto updateUser(UserDto user) {

        User userModel = userRepository.getUserByEmail(user.getEmail())
                .orElseThrow(IllegalArgumentException::new);
        userModel.setAvatar(user.getAvatar());
        userModel.setNickname(user.getNickname());
        userModel.setEmail(user.getEmail());
        userModel.setFirstName(user.getFirstName());
        userModel.setSecondName(user.getSecondName());

        return UserDto.fromModel(userRepository.save(userModel));
    }

    @Override
    public UserDto updateAvatar(MultipartFile file, UserDto userDto) {

        try {
            File avatar = ImageHelper.makeFile(file);
            String filename = "profilePhoto" + userDto.getId();

            Map upload = CloudinaryHelper.getInstance().uploader()
                    .upload(file, ObjectUtils.asMap("public_id", filename));
            String url = (String) upload.get("url");
            userDto.setAvatar(url);
            updateUser(userDto);
            avatar.delete();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return userDto;
    }
}
