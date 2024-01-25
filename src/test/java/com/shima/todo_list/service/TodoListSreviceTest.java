package com.shima.todo_list.service;

import com.shima.todo_list.entity.Todo;
import com.shima.todo_list.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoListSreviceTest {

    @InjectMocks
    TodoService todoListService;

    @Mock
    TodoMapper todoListMapper;

    @Test
    public void 存在するユーザーのIDを指定したときに正常にユーザーが返されること() {
        doReturn(Optional.of(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null))).when(todoListMapper).findById(1);
        Todo actual = todoListService.findById(1);
        assertThat(actual).isEqualTo(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null));
        verify(todoListMapper).findById(1);
    }
}
