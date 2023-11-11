package com.example.webregister;

import com.example.webregister.validator.CompareUnique;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ListMapper listMapper(){
        return ListMapper.getInstance();
    }
    @Bean
    public CompareUnique compareUnique() {
        return new CompareUnique();
    }
}
