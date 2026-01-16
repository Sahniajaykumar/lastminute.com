package com.lastminute.com.service;

import com.lastminute.com.entity.User;
import com.lastminute.com.payload.APIResponse;
import com.lastminute.com.payload.UserDto;
import com.lastminute.com.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public APIResponse<String> registration(UserDto userDto){

        APIResponse<String> response = new APIResponse<String>();

        if(userRepository.existsByUsername(userDto.getUsername())){
            response.setMessage("Registration Failed");
            response.setStatus(400);
            response.setData("Username already exists");
            return response;
        }

        if(userRepository.existsByEmail(userDto.getEmail())){
            response.setMessage("Registration Failed");
            response.setStatus(400);
            response.setData("Email already exists");
            return response;
        }

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);   // JpaRepository.save() NEVER returns null,If something fails → exception is thrown

        response.setMessage("Registration Success");
        response.setStatus(201);
        response.setData("User Registered Successfully");
        return response;
    }

}
