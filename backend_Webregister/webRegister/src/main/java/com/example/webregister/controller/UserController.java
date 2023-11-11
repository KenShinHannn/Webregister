package com.example.webregister.controller;

import com.example.webregister.ListMapper;
import com.example.webregister.config.JwtTokenUtil;
import com.example.webregister.dto.UserResponse;
import com.example.webregister.entity.Users;
import com.example.webregister.services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping("/users")
    public UserResponse getAllUser(HttpServletRequest request){
        String Header = request.getHeader("Authorization");
        if(Header != null){
            String token = Header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = userServices.getUser(userServices.getUserFromToken(username));
            return modelMapper.map(users, UserResponse.class);
        }
        return null;

    }

    @PostMapping("/register")
    public UserResponse addNewUser(@Valid @RequestBody Users newUsers){
        Users users = userServices.addUser(newUsers);
        return modelMapper.map(users, UserResponse.class);
    }
}
