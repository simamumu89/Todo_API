package com.shima.todo_list.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {

        super(message);
    }

}
