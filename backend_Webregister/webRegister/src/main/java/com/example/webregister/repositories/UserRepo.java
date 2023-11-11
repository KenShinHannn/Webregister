package com.example.webregister.repositories;

import com.example.webregister.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String name);
    Users findByEmail(String email);
}
