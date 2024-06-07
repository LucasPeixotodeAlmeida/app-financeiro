package com.projeto.app_financeiro.user.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.app_financeiro.user.dto.UserDTO;
import com.projeto.app_financeiro.user.entities.UserEntity;
import com.projeto.app_financeiro.user.enums.UserRoleEnum;
import com.projeto.app_financeiro.user.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) {

        if (this.userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encryptedPassword);
        user.setRole(UserRoleEnum.USER);

        this.userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    
}
