package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserDto save(CreateUserDto user);

    UserDto getUserByEmail(String email);

    User getRawUserByEmail(String email);

    UserDto updateUser(UserDto user);

    UserDto updateAvatar(MultipartFile file, UserDto userDto);

    void deleteUser(Integer userId);
}
