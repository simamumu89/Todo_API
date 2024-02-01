package com.shima.todo_list.service;

import com.shima.todo_list.entity.Todo;
import com.shima.todo_list.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {

    @InjectMocks
    TodoService todoListService;

    @Mock
    TodoMapper todoListMapper;

    //GETのテストコード
    @Test
    void findAllで存在するタスクを全部取得すること() {
        List<Todo> todo = List.of(
                new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null),
                new Todo(2, "API作成", LocalDate.of(2023, 12, 7), null, null),
                new Todo(3, "テスト", LocalDate.of(2023, 12, 8), null, null),
                new Todo(4, "リリース", LocalDate.of(2023, 12, 9), null, null)

        );
        doReturn(todo).when(todoListMapper).findAll();
        List<Todo> actual = todoListService.getTodoList();
        assertThat(actual).isEqualTo(todo);
        verify(todoListMapper).findAll();
    }

    @Test
    public void 存在するIDを指定したときに正常にタスクが返されること() {
        doReturn(Optional.of(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null))).when(todoListMapper).findById(1);
        Todo actual = todoListService.findById(1);
        assertThat(actual).isEqualTo(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null));
        verify(todoListMapper).findById(1);
    }
}
