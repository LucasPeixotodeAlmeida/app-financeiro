package com.projeto.app_financeiro.user.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.app_financeiro.user.entities.UserEntity;
import com.projeto.app_financeiro.user.repositories.UserRepository;

@Service
public class AuthUserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> findUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
}
    
