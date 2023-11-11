package com.example.webregister.entity;

import com.example.webregister.validator.ValidatePassword;
import com.example.webregister.validator.ValidateUnique;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {
    @NotBlank
    @Size(min = 1,max = 45)
    @Column(unique = true)
    @ValidateUnique(property = "username")
    private String username;
    @NotBlank
    @ValidatePassword
    private String password;
}