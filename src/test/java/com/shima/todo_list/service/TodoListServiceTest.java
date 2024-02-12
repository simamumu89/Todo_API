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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    //READ機能のテスト(全件取得)GET
    @Test
    void findAllで存在するタスク情報を全部取得すること() {
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

    //READ機能のテスト(指定id)GET
    @Test
    public void 存在するIDを指定したときに正常にタスク情報が返されること() {
        doReturn(Optional.of(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null))).when(todoListMapper).findById(1);
        Todo actual = todoListService.findById(1);
        assertThat(actual).isEqualTo(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null));
        verify(todoListMapper).findById(1);
    }

    //READ機能のテスト(指定idの例外)GET
    @Test
    public void 存在しないIDを指定したときに例外処理が動作すること() throws
            TaskNotFoundException {
        doReturn(Optional.empty()).when(todoListMapper).findById(99);
        assertThrows(TaskNotFoundException.class, () -> {
            todoListService.findById(99);
        });
        verify(todoListMapper).findById(99);
    }

    //CREATE機能のテスト(POST)
    @Test
    public void 存在しないタスク情報を新規登録すること() {
        Todo todo = new Todo("詳細", LocalDate.of(2023, 12, 10), null, null);
        doNothing().when(todoListMapper).insert(todo);
        Todo actual = todoListService.insert("詳細", LocalDate.of(2023, 12, 10), null, null);
        assertThat(actual).isEqualTo(new Todo("詳細", LocalDate.of(2023, 12, 10), null, null));
        verify(todoListMapper).insert(todo);
    }

    //UPDATE機能のテスト(PATCH)
    @Test
    public void 存在するIDのタスク情報を更新すること() {
        doReturn(Optional.of(new Todo(3, "テスト", LocalDate.of(2023, 12, 8), null, null))).when(todoListMapper).findById(3);
        todoListService.update(3, "テストチェック", LocalDate.of(2023, 12, 9), LocalDate.of(2023, 12, 10));
        verify(todoListMapper).findById(3);
        verify(todoListMapper).update(new Todo(3, "テストチェック", LocalDate.of(2023, 12, 9), LocalDate.of(2023, 12, 10)));
    }

    @Test
    public void 指定したIDが存在しないとき例外処理が動作すること() {
        doReturn(Optional.empty()).when(todoListMapper).findById(99);
        assertThatThrownBy(() -> todoListService.update(99, "テストチェック", LocalDate.of(2023, 12, 9), LocalDate.of(2023, 12, 10)))
                .isInstanceOfSatisfying(TaskNotFoundException.class, e -> {
                    assertThat(e.getMessage()).isEqualTo("task not found");
                });
        verify(todoListMapper).findById(99);
    }

    //DELETE機能のテスト
    @Test
    public void 指定したIDのデータが削除できること() {
        doReturn(Optional.of(new Todo(2, "API作成", LocalDate.of(2023, 12, 7), null, null))).when(todoListMapper).findById(2);
        todoListService.findById(2);
        verify(todoListMapper).findById(2);
        verify(todoListMapper).findById(2);

    }

    @Test
    public void 存在しないIDのデータを削除したときに例外処理が動作こと() {
        doReturn(Optional.empty()).when(todoListMapper).findById(99);

        assertThatThrownBy(
                () -> todoListService.findById(99)
        ).isInstanceOfSatisfying(
                TaskNotFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("task not found"));
        verify(todoListMapper).findById(99);
    }
}
