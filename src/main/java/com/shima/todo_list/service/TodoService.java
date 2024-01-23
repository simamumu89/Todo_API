package com.shima.todo_list.service;

import com.shima.todo_list.entity.Todo;
import com.shima.todo_list.exception.TaskNotFoundException;
import com.shima.todo_list.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {

        this.todoMapper = todoMapper;
    }

    //GET(全件取得)
    public List<Todo> getTodoList() {
        List<Todo> todoLists = todoMapper.findAll();
        return todoLists;
    }

    //GET(指定id)と例外処理
    public Todo findById(int id) {
        Optional<Todo> todoList = this.todoMapper.findById(id);
        if (todoList.isPresent()) {
            return todoList.get();
        } else {
            throw new TaskNotFoundException("task not found");
        }
    }


    //Post(新規追加登録処理）
    public Todo insert(String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        Todo todoList = new Todo(name, startDate, scheduledEndDate, actualEndDate);
        todoMapper.insert(todoList);
        return todoList;
    }

    //PATCH(既存DBの部分更新）
    public void update(int id, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        //指定したIDを返す　
        todoMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));
        Todo todoList = new Todo(id, scheduledEndDate, actualEndDate);
        todoMapper.update(todoList);
    }

    //DELETE(指定したid削除）
    public void delete(int id) {
        todoMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        todoMapper.delete(id);
    }
}
