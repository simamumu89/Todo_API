package com.shima.todo_list.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate scheduledEndDate;
    private LocalDate actualEndDate;


    //GET(全件取得と指定ID）のConstructor

    public Todo(int id, String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;

    }

    //Post(新規登録処理）のConstructor
    public Todo(String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.name = name;
        this.startDate = startDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;
    }

    //PATCH(既存DBの更新)のUpdateResponseのConstructor
    public Todo(int id, String name, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.id = id;
        this.name = name;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getScheduledEndDate() {
        return scheduledEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todoList = (Todo) o;
        return id == todoList.id && Objects.equals(name, todoList.name) && Objects.equals(startDate, todoList.startDate) && Objects.equals(scheduledEndDate, todoList.scheduledEndDate) && Objects.equals(actualEndDate, todoList.actualEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, scheduledEndDate, actualEndDate);
    }
}
