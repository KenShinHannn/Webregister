package com.example.webregister.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponse {
    private String username;
    private String name;
    private String lastname;
    private String email;
    @Column(columnDefinition = "ENUM('male', 'female','other') DEFAULT 'announcer'")
    private String gender;
}
