package com.example.webregister.entity;

import com.example.webregister.validator.ValidateGender;
import com.example.webregister.validator.ValidatePassword;
import com.example.webregister.validator.ValidateUnique;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name= "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotBlank
    @Size(min = 1,max = 45)
    @ValidateUnique(property = "username")
    private String username;
    @NotBlank
    @ValidatePassword
    private String password;
    @NotBlank
    @Size(min = 1,max = 100)
    private String name;
    @NotBlank
    @Size(min = 1,max = 100)
    private String lastname;
    @NotBlank
    @Size(min = 1,max = 150)
    @Email(message = "Email should be valid")
    @ValidateUnique(property = "email")
    private String email;
    @Column(columnDefinition = "ENUM('Male', 'Female','Other') DEFAULT 'announcer'")
    @ValidateGender
    private String gender;
}
