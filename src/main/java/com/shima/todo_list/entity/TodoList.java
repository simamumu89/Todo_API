package com.shima.todo_list.entity;

import java.time.LocalDate;

public class TodoList {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate scheduledEndDate;
    private LocalDate actualEndDate;


    //GET(全件取得と指定ID）のConstructor

    public TodoList(int id, String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;

    }

    //Post(新規登録処理）のConstructor
    public TodoList(String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.name = name;
        this.startDate = startDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;
    }

    //PATCH(既存DBの更新)のUpdateResponseのConstructor
    public TodoList(int id, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.id = id;
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
}
