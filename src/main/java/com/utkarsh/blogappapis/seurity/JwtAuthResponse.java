package com.utkarsh.blogappapis.seurity;

import com.utkarsh.blogappapis.payloads.UserDto;
import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;

    private UserDto user;
}
