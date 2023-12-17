package com.shima.todo_list;

import java.time.LocalDate;

public class CreateRequest {

    private String name;
    private LocalDate start_date;
    private LocalDate scheduled_end_date;
    private LocalDate actual_end_date;

    //POST
    //Request内容(タスク名,タスク開始日,タスク終了予定日,タスク終了実績日)
    public CreateRequest(String name, LocalDate start_date, LocalDate scheduled_end_date, LocalDate actual_end_date) {
        this.name = name;
        this.start_date = start_date;
        this.scheduled_end_date = scheduled_end_date;
        this.actual_end_date = actual_end_date;
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
