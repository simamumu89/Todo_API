package com.shima.todo_list;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateRequest {

    @NotNull
    private String name;
    @NotNull
    private LocalDate startDate;
    private LocalDate scheduledEndDate;
    private LocalDate actualEndDate;

    //POST
    //Request内容(タスク名,タスク開始日,タスク終了予定日,タスク終了実績日)

    public CreateRequest(String name, LocalDate startDate, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.name = name;
        this.startDate = startDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;
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
