package com.shima.todo_list.srevice;

import com.shima.todo_list.NameAlreadyExistsException;
import com.shima.todo_list.StartDateAlreadyExistsException;
import com.shima.todo_list.entity.TodoList;
import com.shima.todo_list.foundexception.TaskNotFoundException;
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
    public TodoList insert(String name, LocalDate start_date, LocalDate scheduled_end_date, LocalDate actual_end_date) {
        // nameがすでに存在するかどうかのチェック
        boolean isNamePresent = this.todoListMapper.findByName(name).isPresent();
        if (isNamePresent) {
            throw new NameAlreadyExistsException("Already registered data");
        }
        // addressがすでに存在するかどうかのチェック
        boolean isStartDatePresent = this.todoListMapper.findByStart_Date(start_date).isPresent();
        if (isStartDatePresent) {
            throw new StartDateAlreadyExistsException("Already registered data");
        }
        TodoList todoList = new TodoList(name, start_date, scheduled_end_date, actual_end_date);
        todoListMapper.insert(todoList);
        return todoList;
    }
}
