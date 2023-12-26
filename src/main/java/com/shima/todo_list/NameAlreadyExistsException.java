package com.shima.todo_list;

public class NameAlreadyExistsException extends RuntimeException {

    public NameAlreadyExistsException(String message) {

        super(message);
    }
}
