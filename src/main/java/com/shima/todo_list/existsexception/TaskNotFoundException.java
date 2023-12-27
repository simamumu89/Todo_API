package com.shima.todo_list.existsexception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {//文字列メッセージ

        super(message);
    }

}
