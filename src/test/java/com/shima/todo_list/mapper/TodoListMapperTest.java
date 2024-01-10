package com.shima.todo_list.mapper;


import com.shima.todo_list.entity.TodoList;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoListMapperTest {

    @Autowired
    TodoListMapper todoListMapper;

    @Test
    @Sql(
            scripts = {"classpath:/sqlannotation/delete-todo_lists.sql", "classpath:/sqlannotation/insert-todo_lists.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void すべてのユーザーが取得できること() {
        List<TodoList> todoLists = todoListMapper.findAll();
        assertThat(todoLists)
                .hasSize(4)
                .contains(
                        new TodoList(1, "構想", LocalDate.of(2023, 12, 6), null, null),
                        new TodoList(2, "API作成", LocalDate.of(2023, 12, 7), null, null),
                        new TodoList(3, "テスト", LocalDate.of(2023, 12, 8), null, null),
                        new TodoList(4, "リリース", LocalDate.of(2023, 12, 9), null, null)
                );
    }
}