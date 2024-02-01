package com.shima.todo_list.service;

import com.shima.todo_list.entity.Todo;
import com.shima.todo_list.exception.TaskNotFoundException;
import com.shima.todo_list.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    private final TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {

        this.todoMapper = todoMapper;
    }

    //GET(全件取得)
    public List<Todo> getTodoList() {
        List<Todo> todo = todoMapper.findAll();
        return todo;
    }

    //GET(指定id)と例外処理
    public Todo findById(int id) throws TaskNotFoundException {
        return todoMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));
    }


    //Post(新規追加登録処理）
    public Todo insert(String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        Todo todo = new Todo(name, startDate, scheduledEndDate, actualEndDate);
        todoMapper.insert(todo);
        return todo;
    }

    //PATCH(既存DBの部分更新）
    public void update(int id, String name, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        //指定したIDを返す　
        todoMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));
        Todo todo = new Todo(id, name, scheduledEndDate, actualEndDate);
        todoMapper.update(todo);
    }

    //DELETE(指定したid削除）
    public void delete(int id) {
        todoMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        todoMapper.delete(id);
    }

}
