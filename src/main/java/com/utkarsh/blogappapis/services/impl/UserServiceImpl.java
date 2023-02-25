package com.utkarsh.blogappapis.services.impl;

import com.utkarsh.blogappapis.config.AppConstants;
import com.utkarsh.blogappapis.entity.Role;
import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.exception.DuplicateEntryException;
import com.utkarsh.blogappapis.exception.ResourceNotFoundException;
import com.utkarsh.blogappapis.payloads.UserDto;
import com.utkarsh.blogappapis.repository.RoleRepo;
import com.utkarsh.blogappapis.repository.UserRepo;
import com.utkarsh.blogappapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        if (userRepo.existsByEmail(userDto.getEmail())){
            throw new DuplicateEntryException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = userRepo.save(user);

        return modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        if (userRepo.existsByEmail(userDto.getEmail())){
            throw new DuplicateEntryException();
        }
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        if (userRepo.existsByEmail(userDto.getEmail())){
            throw new DuplicateEntryException();
        }
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        userRepo.delete(user);
    }
}
