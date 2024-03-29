package com.shima.todo_list.mapper;

import com.shima.todo_list.entity.Todo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {

    //全DBの情報を取得
    @Select("SELECT * FROM todo_lists")
    List<Todo> findAll();


    //GET　指定したid
    @Select("SELECT * FROM todo_lists WHERE id = #{id}")
    Optional<Todo> findById(int id);

    //POST処理 (新規追加登録処理)
    @Insert("INSERT INTO todo_lists (name, start_date, scheduled_end_date, actual_end_date) VALUES (#{name},#{startDate},#{scheduledEndDate}, #{actualEndDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Todo todoList);

    //POST Validation1
    @Select("SELECT * FROM todo_lists WHERE name = #{name}")
    Optional<Todo> findByName(String name);

    //POST Validation2
    @Select("SELECT * FROM todo_lists WHERE start_date = #{startDate}")
    Optional<Todo> findByStartDate(LocalDate startDate);


    //PATCH (既存DBの部分更新）
    @Update("UPDATE todo_lists SET name = #{name}, scheduled_end_date = #{scheduledEndDate}, actual_end_date = #{actualEndDate} WHERE id = #{id}")
    void update(Todo todoList);

    //DELETE(指定したidを削除）
    @Delete("DELETE FROM todo_lists WHERE id =#{id}")
    void delete(int id);
}
