package com.erik.todoapp.usecase.user;

import com.erik.todoapp.domain.entity.User;
import com.erik.todoapp.domain.port.respository.UserRepository;
import com.erik.todoapp.usecase.helper.PasswordProtector;

public class UserRegistration {
    private final UserRepository userRepository;
    private final User user;

    public UserRegistration(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    public User execute() {
        user.setPassword(new PasswordProtector().encrypt(user.getPassword()));
        return userRepository.save(user);
    }
}
