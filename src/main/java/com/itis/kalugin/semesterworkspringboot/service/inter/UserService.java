package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;

public interface UserService {

    UserDto save(CreateUserDto user);
}
