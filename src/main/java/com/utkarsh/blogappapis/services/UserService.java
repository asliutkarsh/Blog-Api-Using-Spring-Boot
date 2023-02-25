package com.utkarsh.blogappapis.services;

import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Long userId);
    List<UserDto> getAllUser();
    UserDto getUserById(Long userId);
    void deleteUser(Long userId);
}
