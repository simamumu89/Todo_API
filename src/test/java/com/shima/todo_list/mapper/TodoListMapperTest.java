package com.shima.todo_list.mapper;


import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.shima.todo_list.entity.Todo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoListMapperTest {

    @Autowired
    TodoMapper todoListMapper;


    //READ機能のDBテスト(全件取得)
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    void すべてのタスクが取得できること() {
        List<Todo> todoList = todoListMapper.findAll();
        assertThat(todoList)
                .hasSize(4)
                .contains(
                        new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null),
                        new Todo(2, "API作成", LocalDate.of(2023, 12, 7), null, null),
                        new Todo(3, "テスト", LocalDate.of(2023, 12, 8), null, null),
                        new Todo(4, "リリース", LocalDate.of(2023, 12, 9), null, null)
                );
    }

    @Test
    @DataSet(value = "datasets/todolists.yml")

    //READ機能のDBテスト(指定したID)
    @Transactional
    void 指定したIDのタスク情報を獲得すること() {
        Optional<Todo> todoLists = todoListMapper.findById(1);
        assertThat(todoLists).contains(new Todo(1, "構想", LocalDate.of(2023, 12, 6), null, null));
    }

    @Test
    @DataSet(value = "datasets/todolists.yml")
    //READ機能のDBテスト(指定したIDが存在しない場合)
    @Transactional
    void 存在しないIDを指定する場合に空の情報を獲得すること() {
        Optional<Todo> todo = todoListMapper.findById(5);
        assertThat(todo).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/insert_todolists.yml", ignoreCols = "id")

    //CREATE機能のDBテスト(DBRider)
    @Transactional
    void 新規のタスクを登録すること() {
        Todo todo = new Todo("承認", LocalDate.of(2023, 12, 10), null, null);
        todoListMapper.insert(todo);
    }

    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/update_todolists.yml")

    //UPDATE機能のDBテスト
    @Transactional
    public void 存在するタスクを更新すること() {
        Todo todo = new Todo(1, "設計", LocalDate.of(2023, 12, 6), LocalDate.of(2023, 12, 8), LocalDate.of(2023, 12, 9));
        todoListMapper.update(todo);
    }

    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/todolists.yml")

    @Transactional
    public void 存在しないidでタスクを更新処理した場合はテーブルのレコードは更新されないこと() {
        Todo todo = new Todo(99, "詳細設計", LocalDate.of(2023, 12, 4), null, null);
        todoListMapper.update(todo);
    }

    //DELETE機能のDBテスト
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/delete_todolists.yml")
    @Transactional
    public void 存在するタスク情報を削除すること() {
        todoListMapper.delete(3);
    }

    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/todolists.yml")
    @Transactional
    public void 存在しないidのタスク情報を指定した場合は削除されないこと() {
        todoListMapper.delete(99);
    }
}


