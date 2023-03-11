package com.blog.blog.service;

import com.blog.blog.payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto dto);

    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getALLUsers();

    void deleteUser(Integer userId);
}
