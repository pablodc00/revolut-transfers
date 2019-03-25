package com.revolut.service;

import java.util.List;
import java.util.Optional;

import com.revolut.model.User;

public interface UserService {
    List<User> getUsers();
    
    Optional<User> getUserById(String userId);
}
