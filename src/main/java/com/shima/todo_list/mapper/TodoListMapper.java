package com.shima.todo_list.mapper;

import com.shima.todo_list.entity.TodoList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoListMapper {

    //全DBの情報を取得
    @Select("SELECT * FROM todo_lists")
    List<TodoList> findAll(); // Entityから受け取る


    //GET　指定したid
    @Select("SELECT * FROM todo_lists WHERE id = #{id}")
    Optional<TodoList> findById(int id);

    //POST処理 (新規追加登録処理)
    @Insert("INSERT INTO todo_lists (name, start_date, scheduled_end_date, actual_end_date) VALUES (#{name},#{start_date},#{scheduled_end_date}, #{actual_end_date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(TodoList todoList);

    //POST Validation1
    @Select("SELECT * FROM todo_lists WHERE name = #{name}")
    Optional<TodoList> findByName(String name);

    //POST Validation2
    @Select("SELECT * FROM todo_lists WHERE start_date = #{start_date}")
    Optional<TodoList> findByStart_Date(LocalDate start_date);

    
    //PATCH (既存DBの部分更新）
    @Update("UPDATE todo_lists SET scheduled_end_date = #{scheduled_end_date}, actual_end_date = #{actual_end_date} WHERE id = #{id}")
    void update(TodoList todoList);

}
