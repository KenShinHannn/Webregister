package com.example.webregister.services;

import com.example.webregister.entity.Users;
import com.example.webregister.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;

    public Users getUser(Users users){

        return userRepo.findByUsername(users.getUsername());
    }
    public Users getUserFromToken(String token) {
        if (token != null) {
            return userRepo.findByUsername(token);
        }
        return null;
    }
    public Users addUser(Users usersForInsert){
        Users users = new Users();
        users.setUsername(usersForInsert.getUsername().trim());
        users.setName(usersForInsert.getName().trim());
        users.setLastname(usersForInsert.getLastname().trim());
        users.setEmail(usersForInsert.getEmail().trim());
        users.setGender(usersForInsert.getGender().trim());
        String encodePassword = encodePassword(usersForInsert.getPassword());
        users.setPassword(encodePassword);
        Users newUsers = userRepo.saveAndFlush(users);
        return newUsers;
    }

    public String encodePassword(String password){
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16,32,1,4096,1);
        String encodePassword = argon2PasswordEncoder.encode(password);
        return encodePassword;
    }
}
