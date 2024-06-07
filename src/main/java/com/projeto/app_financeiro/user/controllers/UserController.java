package com.projeto.app_financeiro.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.app_financeiro.user.dto.UserDTO;
import com.projeto.app_financeiro.user.entities.UserEntity;
import com.projeto.app_financeiro.user.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        UserEntity registredUser = userService.registerUser(userDTO);
        userDTO.setId(registredUser.getId());
        return userDTO;
    }

    
}
