package com.shima.todo_list;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    public TodoListService(TodoListMapper todoListMapper) {

        this.todoListMapper = todoListMapper;
    }

    public List<TodoList> getTodoList() {
        List<TodoList> todoLists = todoListMapper.findAll();
        return todoLists;
    }

    public TodoList findById(int id) {
        Optional<TodoList> todoList = this.todoListMapper.findById(id);
        if (todoList.isPresent()) {
            return todoList.get();
        } else {
            throw new TaskNotFoundException("task not found");
        }
    }
}
