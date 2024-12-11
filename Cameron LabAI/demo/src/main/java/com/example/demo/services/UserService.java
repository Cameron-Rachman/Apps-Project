package com.example.demo.services;

import com.example.demo.entities.User;

public interface UserService {
    User createUser(User user);
    User resetPassword(String username, String newPassword);
    User toggleUnlocked(String username);
    void deleteUser(String username);
}