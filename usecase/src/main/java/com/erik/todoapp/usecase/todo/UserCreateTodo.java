package com.erik.todoapp.usecase.todo;

import com.erik.todoapp.domain.entity.Todo;
import com.erik.todoapp.domain.entity.User;
import com.erik.todoapp.domain.port.respository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class UserCreateTodo {
    private final UserRepository userRepository;
    private final User user;
    private final Todo todo;

    public Todo execute() {
        if (user.getTodoList() == null) {
            user.setTodoList(new ArrayList<>());
        }
        user.getTodoList().add(todo);
        userRepository.save(user);
        return todo;
    }
}
