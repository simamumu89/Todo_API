package com.shima.todo_list.existsexception;

public class StartDateAlreadyExistsException extends RuntimeException {

    public StartDateAlreadyExistsException(String message) {

        super(message);
    }
}
