package com.shima.todo_list;

import java.time.LocalDate;

public class UpdateRequest {

    private LocalDate scheduledEndDate;
    private LocalDate actualEndDate;

    public UpdateRequest(LocalDate scheduledEndDate, LocalDate actualEndDate) {
        this.scheduledEndDate = scheduledEndDate;
        this.actualEndDate = actualEndDate;
    }

    public LocalDate getScheduledEndDate() {
        return scheduledEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }
}
