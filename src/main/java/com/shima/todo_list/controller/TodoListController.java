package com.shima.todo_list.controller;

import com.shima.todo_list.CreateRequest;
import com.shima.todo_list.CreateResponse;
import com.shima.todo_list.entity.TodoList;
import com.shima.todo_list.srevice.TodoListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }


    @GetMapping("/todo_lists")
    public List<TodoList> getTodoList() {
        List<TodoList> todoLists = todoListService.getTodoList();
        return todoLists;
    }

    //GET
    //idで指定したデータ取得と例外処理
    @GetMapping("/todo_lists/{id}")
    public TodoList getTodoList(@PathVariable("id") int id) {
        return todoListService.findById(id);
    }

    //POST (Create)
    //新規登録(ID追加）
    @PostMapping("/todo_lists")
    public CreateResponse createTodoList(@RequestBody CreateRequest createRequest) {
        return new CreateResponse("Add new task");
    }
}
