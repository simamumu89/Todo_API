package com.shima.todo_list;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {//文字列メッセージ

        super(message);
    }

}
