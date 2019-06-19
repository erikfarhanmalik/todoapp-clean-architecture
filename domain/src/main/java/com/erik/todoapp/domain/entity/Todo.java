package com.erik.todoapp.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
    private String id;
    private String todo;
    private boolean isDone;

    public Todo(String todo) {
        this.todo = todo;
    }

    public Todo(String id, String todo) {
        this.id = id;
        this.todo = todo;
    }
}
