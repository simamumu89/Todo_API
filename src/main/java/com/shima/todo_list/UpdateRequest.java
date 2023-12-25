package com.shima.todo_list;

import java.time.LocalDate;

public class UpdateRequest {

    private LocalDate ScheduledEndDate;
    private LocalDate ActualEndDate;

    public UpdateRequest(LocalDate scheduledEndDate, LocalDate actualEndDate) {
        ScheduledEndDate = scheduledEndDate;
        ActualEndDate = actualEndDate;
    }

    public LocalDate getScheduledEndDate() {
        return ScheduledEndDate;
    }

    public LocalDate getActualEndDate() {
        return ActualEndDate;
    }
}
