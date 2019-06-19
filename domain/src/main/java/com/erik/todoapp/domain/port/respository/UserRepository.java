package com.erik.todoapp.domain.port.respository;

import com.erik.todoapp.domain.entity.User;

public interface UserRepository {
    User save(User user);

    User findByEmail(String email);
}
