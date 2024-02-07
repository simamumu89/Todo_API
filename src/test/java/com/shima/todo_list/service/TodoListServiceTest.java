package com.shima.todo_list.service;

import com.shima.todo_list.entity.Todo;
import com.shima.todo_list.exception.TaskNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {

    @InjectMocks
    TodoService todoListService;

    @Mock
    TodoMapper todoListMapper;

    //READ機能のテスト(全件取得)
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

    //READ機能のテスト(指定id)
    @Test
    public void 存在するIDを指定したときに正常にタスクが返されること() {
        doReturn(Optional.of(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null))).when(todoListMapper).findById(1);
        Todo actual = todoListService.findById(1);
        assertThat(actual).isEqualTo(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null));
        verify(todoListMapper).findById(1);
    }

    //READ機能のテスト(指定idの例外)
    @Test
    public void 存在しないIDを指定したときに例外処理が動作すること() throws
            TaskNotFoundException {
        doReturn(Optional.empty()).when(todoListMapper).findById(99);
        assertThrows(TaskNotFoundException.class, () -> {
            todoListService.findById(99);
        });
        verify(todoListMapper).findById(99);
    }

    //CREATE機能のテスト
    @Test
    public void 存在しないタスク情報を新規登録すること() {
        Todo todo = new Todo("詳細", LocalDate.of(2023, 12, 10), null, null);
        doNothing().when(todoListMapper).insert(todo);
        Todo actual = todoListService.insert("詳細", LocalDate.of(2023, 12, 10), null, null);
        assertThat(actual).isEqualTo(new Todo("詳細", LocalDate.of(2023, 12, 10), null, null));
        verify(todoListMapper).insert(todo);
    }
}
