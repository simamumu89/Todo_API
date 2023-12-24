package com.shima.todo_list.entity;

import java.time.LocalDate;

public class TodoList {

    private int id;
    private String name;
    private LocalDate start_date;
    private LocalDate scheduled_end_date;
    private LocalDate actual_end_date;


    //GET(全件取得と指定ID）のConstructor
    public TodoList(int id, String name, LocalDate start_date, LocalDate scheduled_end_date, LocalDate actual_end_date) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.scheduled_end_date = scheduled_end_date;
        this.actual_end_date = actual_end_date;
    }

    //Post(新規登録処理）のConstructor
    public TodoList(String name, LocalDate start_date, LocalDate scheduled_end_date, LocalDate actual_end_date) {
        this.name = name;
        this.start_date = start_date;
        this.scheduled_end_date = scheduled_end_date;
        this.actual_end_date = actual_end_date;
    }
    
    //PATCH(既存DBの更新)のUpdateResponseのConstructor
    public TodoList(int id, LocalDate scheduled_end_date, LocalDate actual_end_date) {
        this.id = id;
        this.scheduled_end_date = scheduled_end_date;
        this.actual_end_date = actual_end_date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getScheduled_end_date() {
        return scheduled_end_date;
    }

    public LocalDate getActual_end_date() {
        return actual_end_date;
    }
}
