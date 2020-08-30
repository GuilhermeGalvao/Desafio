package com.example.Desafio.configuration;

import com.example.Desafio.service.EncryptService;
import com.example.Desafio.service.UniqueTypeService;
import com.example.Desafio.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(){
        return new UserService();
    }


    @Bean
    public UniqueTypeService uniqueTypeService(){
        return new UniqueTypeService();
    }

    @Bean
    public EncryptService encryptService(){
        return new EncryptService();
    }

}