package com.erik.todoapp.usecase.todo;

import com.erik.todoapp.domain.entity.Todo;
import com.erik.todoapp.domain.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserGetAllTodo {

    private final User user;

    public List<Todo> execute() {
        return Optional.ofNullable(user.getTodoList()).orElseGet(ArrayList::new);
    }
}
