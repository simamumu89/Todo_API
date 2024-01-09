package com.shima.todo_list.service;

import com.shima.todo_list.entity.TodoList;
import com.shima.todo_list.mapper.TodoListMapper;
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
    TodoListService todoListService;

    @Mock
    TodoListMapper todoListMapper;

    @Test
    public void 存在するユーザーのIDを指定したときに正常にユーザーが返されること() {
        doReturn(Optional.of(new TodoList(1, "構想", LocalDate.of(2023, 12, 6), null, null))).when(todoListMapper).findById(1);
        TodoList actual = todoListService.findById(1);
        assertThat(actual).isEqualTo(new TodoList(1, "構想", LocalDate.of(2023, 12, 6), null, null));
        verify(todoListMapper).findById(1);
    }
}
