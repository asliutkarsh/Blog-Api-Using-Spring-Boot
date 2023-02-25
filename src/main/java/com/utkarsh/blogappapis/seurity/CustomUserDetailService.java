package com.utkarsh.blogappapis.seurity;


import com.utkarsh.blogappapis.entity.User;
import com.utkarsh.blogappapis.exception.ResourceNotFoundException;
import com.utkarsh.blogappapis.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User","email "+username,0L));
        return user;
    }
}
