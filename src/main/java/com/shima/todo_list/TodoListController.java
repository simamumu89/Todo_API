package com.shima.todo_list;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;


@RestController
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    //GET 指定したIDが存在しない場合
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handTaskNotFoundException(
            UserNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }//404エラーを返す


    @GetMapping("/todo_lists")
    public List<TodoList> getTodoList() {
        List<TodoList> todoLists = todoListService.getTodoList();
        return todoLists;
    }

    //GET
    //idで指定したデータ取得と例外処理
    @GetMapping("/todo_lists/{id}")
    public TodoList getTodoList(@PathVariable("id") int id) throws UserNotFoundException {
        return todoListService.findById(id);
    }
}
