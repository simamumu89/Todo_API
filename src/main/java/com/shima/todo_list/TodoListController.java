package com.shima.todo_list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
