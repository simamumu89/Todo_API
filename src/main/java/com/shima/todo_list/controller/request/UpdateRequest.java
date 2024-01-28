package com.shima.todo_list.controller.request;

import java.time.LocalDate;

public class UpdateRequest {

    private String name;
    private LocalDate scheduledEndDate;
    private LocalDate actualEndDate;

    public UpdateRequest(String name, LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.name = name;
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getScheduledEndDate() {
        return scheduledEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }
}
