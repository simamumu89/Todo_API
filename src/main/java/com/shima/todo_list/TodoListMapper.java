package com.shima.todo_list;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

}
