package com.shima.todo_list;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
