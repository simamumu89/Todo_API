package com.shima.todo_list.existsexception;

public class NameAlreadyExistsException extends RuntimeException {

    public NameAlreadyExistsException(String message) {

        super(message);
    }
}
