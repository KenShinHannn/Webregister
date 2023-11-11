package com.example.webregister.services;


import com.example.webregister.entity.Users;
import com.example.webregister.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;

    public UserDetails loadUserByUsername(String username )  {
        Users user = userRepo.findByUsername(username);
        if (user == null ) {
            throw new UsernameNotFoundException("The specified username DOES NOT exist");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }


}
