package com.shima.todo_list;

import java.time.LocalDate;

public class UpdateRequest {

    private LocalDate scheduled_end_date;
    private LocalDate actual_end_date;

    public UpdateRequest(LocalDate scheduled_end_date, LocalDate actual_end_date) {
        this.scheduled_end_date = scheduled_end_date;
        this.actual_end_date = actual_end_date;
    }

    public LocalDate getScheduled_end_date() {
        return scheduled_end_date;
    }

    public LocalDate getActual_end_date() {
        return actual_end_date;
    }
}
