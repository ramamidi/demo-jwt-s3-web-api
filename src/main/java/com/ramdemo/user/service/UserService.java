package com.ramdemo.user.service;

import java.util.Optional;

import com.ramdemo.user.model.User;

public interface UserService {
    public Optional<User> getByUsername(String username);
}
