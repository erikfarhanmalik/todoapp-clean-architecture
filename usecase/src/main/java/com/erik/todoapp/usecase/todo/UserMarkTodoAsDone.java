package com.erik.todoapp.usecase.todo;

import com.erik.todoapp.domain.entity.Todo;
import com.erik.todoapp.domain.entity.User;
import com.erik.todoapp.domain.port.respository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMarkTodoAsDone {

    private final UserRepository userRepository;
    private final User user;
    private final Todo todo;

    public Todo execute() {
        user.getTodoList().stream()
                .filter(item -> item.getId().equals(todo.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User does not possess that todo!"));

        user.setTodoList(user.getTodoList()
                .stream()
                .map(item -> {
                    if (item.getId().equals(todo.getId())) {
                        todo.setDone(true);
                        return todo;
                    }
                    return item;
                })
                .collect(Collectors.toList()));

        userRepository.save(user);
        return todo;
    }
}
