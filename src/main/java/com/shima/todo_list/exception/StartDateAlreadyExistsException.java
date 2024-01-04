package com.shima.todo_list.exception;

public class StartDateAlreadyExistsException extends RuntimeException {

    public StartDateAlreadyExistsException(String message) {

        super(message);
    }
}
