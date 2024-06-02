package com.projeto.app_financeiro.user.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.app_financeiro.user.entities.UserEntity;
import com.projeto.app_financeiro.user.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}