package com.shima.todo_list.exception;

public class NameAlreadyExistsException extends RuntimeException {

    public NameAlreadyExistsException(String message) {

        super(message);
    }
}
