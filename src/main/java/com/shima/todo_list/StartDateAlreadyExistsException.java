package com.shima.todo_list;

public class StartDateAlreadyExistsException extends RuntimeException {

    public StartDateAlreadyExistsException(String message) {

        super(message);
    }
}
