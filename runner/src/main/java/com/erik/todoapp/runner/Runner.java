package com.erik.todoapp.runner;

import com.erik.todoapp.domain.entity.Todo;
import com.erik.todoapp.domain.entity.User;
import com.erik.todoapp.domain.port.respository.UserRepository;
import com.erik.todoapp.usecase.todo.UserCreateTodo;
import com.erik.todoapp.usecase.todo.UserGetAllTodo;
import com.erik.todoapp.usecase.todo.UserMarkTodoAsDone;
import com.erik.todoapp.usecase.user.UserAuthentication;
import com.erik.todoapp.usecase.user.UserRegistration;

import java.util.*;

public class Runner {

    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        String email = "erik@gmail.com";
        String password = "ZXasqw12";
        new UserRegistration(userRepository, new User(email, password)).execute();

        boolean execute = new UserAuthentication(userRepository.findByEmail(email), password).execute();
        System.out.println("User authenticated:" + execute);
        System.out.println("<---------------------------->");

        new UserCreateTodo(userRepository, userRepository.findByEmail(email),
                new Todo(UUID.randomUUID().toString(), "Something!"))
                .execute();
        new UserCreateTodo(userRepository, userRepository.findByEmail(email),
                new Todo(UUID.randomUUID().toString(), "Something2!"))
                .execute();
        new UserCreateTodo(userRepository, userRepository.findByEmail(email),
                new Todo(UUID.randomUUID().toString(), "Something3!"))
                .execute();
        System.out.println("TodoList:");
        Optional.ofNullable(userRepository.findByEmail(email).getTodoList())
                .orElseGet(ArrayList::new)
                .forEach(System.out::println);
        System.out.println("<---------------------------->");

        System.out.println("TodoList:");
        new UserGetAllTodo(userRepository.findByEmail(email)).execute().forEach(System.out::println);
        System.out.println("<---------------------------->");

        User user = userRepository.findByEmail(email);
        new UserMarkTodoAsDone(userRepository, user, user.getTodoList().get(1)).execute();
        System.out.println("TodoList:");
        new UserGetAllTodo(userRepository.findByEmail(email)).execute().forEach(System.out::println);
        System.out.println("<---------------------------->");
    }

    private static class InMemoryUserRepositoryImpl implements UserRepository {
        Map<String, User> users = new HashMap<>();

        @Override
        public User save(User user) {
            if (user.getId() == null) {
                String id = UUID.randomUUID().toString();
                user.setId(id);
            }
            users.put(user.getId(), user);
            return user;
        }

        @Override
        public User findByEmail(String email) {
            return users.values()
                    .stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find user with that email"));
        }
    }
}
