package com.shima.todo_list.srevice;

import com.shima.todo_list.entity.TodoList;
import com.shima.todo_list.existsexception.TaskNotFoundException;
import com.shima.todo_list.mapper.TodoListMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    public TodoListService(TodoListMapper todoListMapper) {

        this.todoListMapper = todoListMapper;
    }

    //GET(全件取得)
    public List<TodoList> getTodoList() {
        List<TodoList> todoLists = todoListMapper.findAll();
        return todoLists;
    }

    //GET(指定id)と例外処理
    public TodoList findById(int id) {
        Optional<TodoList> todoList = this.todoListMapper.findById(id);
        if (todoList.isPresent()) {
            return todoList.get();
        } else {
            throw new TaskNotFoundException("task not found");
        }
    }


    //Post(新規追加登録処理）
    public TodoList insert(String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        TodoList todoList = new TodoList(name, startDate, scheduledEndDate, actualEndDate);
        todoListMapper.insert(todoList);
        return todoList;
    }

    //PATCH(既存DBの部分更新）
    public void update(int id, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        //指定したIDを返す　
        todoListMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));
        TodoList todoList = new TodoList(id, scheduledEndDate, actualEndDate);
        todoListMapper.update(todoList);
    }

    //DELETE(指定したid削除）
    public void delete(int id) {
        todoListMapper.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task information not found"));
        todoListMapper.delete(id);
    }
}
