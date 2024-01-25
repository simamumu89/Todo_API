package com.shima.todo_list.controller;

import com.shima.todo_list.controller.request.CreateRequest;
import com.shima.todo_list.controller.request.UpdateRequest;
import com.shima.todo_list.controller.response.CreateResponse;
import com.shima.todo_list.controller.response.DeleteResponse;
import com.shima.todo_list.controller.response.UpdateResponse;
import com.shima.todo_list.entity.Todo;
import com.shima.todo_list.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class TodoListController {

    private final TodoService todoService;

    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/todo_lists")
    public List<Todo> getTodoList() {
        List<Todo> todoList = todoService.getTodoList();
        return todoList;
    }

    //GET
    //idで指定したデータ取得と例外処理
    @GetMapping("/todo_lists/{id}")
    public Todo getTodoList(@PathVariable("id") int id) {
        return todoService.findById(id);
    }

    //POST (Create)
    //新規登録(ID追加）
    @PostMapping("/todo_lists")
    public ResponseEntity<CreateResponse> createTodoList(@RequestBody @Valid CreateRequest createRequest, UriComponentsBuilder uriComponentsBuilder) {
        Todo todo = todoService.insert(createRequest.getName(), createRequest.getStartDate(), createRequest.getScheduledEndDate(), createRequest.getActualEndDate());
        URI uri = uriComponentsBuilder.path("/todo_lists/{id}").buildAndExpand(todo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreateResponse("Add new task"));
    }

    //PATCH (Update)
    //既存DBの情報を更新　Validation追加
    @PatchMapping("/todo_lists/{id}")
    public ResponseEntity<UpdateResponse> updateTodoList(@PathVariable int id, @RequestBody UpdateRequest updateRequest) {
        todoService.update(id, updateRequest.getScheduledEndDate(), updateRequest.getActualEndDate());
        UpdateResponse updateResponse = new UpdateResponse("Update completed!");
        return ResponseEntity.ok(updateResponse);
    }

    //Delete
    //idで指定した情報削除
    @DeleteMapping("/todo_lists/{id}")
    public ResponseEntity<DeleteResponse> deleteTodoList(@PathVariable int id) {
        todoService.delete(id);
        DeleteResponse deleteResponse = new DeleteResponse("Task deleted");
        return ResponseEntity.ok(deleteResponse);
    }
}
