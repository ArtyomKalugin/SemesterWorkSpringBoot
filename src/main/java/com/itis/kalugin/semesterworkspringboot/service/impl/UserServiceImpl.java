package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.itis.kalugin.semesterworkspringboot.dto.ArticleDto;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(UserDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByNicknameLike(String nickname) {
        List<User> allUsers;

        if (nickname.isEmpty() || nickname == null) {
            allUsers = userRepository.findAll();
        } else {
            allUsers = userRepository.getAllByNicknameContains(nickname);
        }

        return allUsers.stream()
                .map(UserDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int userId) {
        return UserDto.fromModel(userRepository.getById(userId));
    }

    @Override
    public UserDto getUserByEmail(String email) {

        Optional<User> optionalUser = userRepository.getUserByEmail(email);

        return optionalUser.map(UserDto::fromModel).orElse(null);

    }

    @Override
    public User getRawUserByEmail(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);

        return user.get();
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
                    .upload(avatar, ObjectUtils.asMap("public_id", filename));
            String url = (String) upload.get("url");
            userDto.setAvatar(url);
            updateUser(userDto);
            avatar.delete();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return userDto;
    }

    @Transactional
    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteUserById(userId);
    }
}
