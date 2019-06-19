package com.erik.todoapp.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String email;
    private String password;
    private List<Todo> todoList;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
