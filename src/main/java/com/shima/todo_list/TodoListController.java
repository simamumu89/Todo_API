package com.shima.todo_list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TodoListController {

    @GetMapping("/todo_Lists")
    public List<TodoList> getTodo_List() {
        return List.of(
                new TodoList(1, "構想", 2023 - 12 - 6, null, null),
                new TodoList(2, "API作成", 2023 - 12 - 7, null, null),
                new TodoList(3, "テスト", 2023 - 12 - 8, null, null),
                new TodoList(4, "リリース", 2023 - 12 - 9, null, null));
    }
}
