package com.erik.todoapp.usecase.user;

import com.erik.todoapp.domain.entity.User;
import com.erik.todoapp.usecase.helper.PasswordProtector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAuthentication {
    private final User user;
    private final String password;

    public boolean execute() {
        return user.getPassword().equals(new PasswordProtector().encrypt(password));
    }
}
