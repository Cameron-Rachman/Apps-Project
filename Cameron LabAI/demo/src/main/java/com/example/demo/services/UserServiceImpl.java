package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User resetPassword(String username, String newPassword) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));
        // Logic to reset password (placeholder as password is not part of entity in this example)
        return user;
    }

    @Override
    public User toggleUnlocked(String username) {
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUnlocked(!user.isUnlocked());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}