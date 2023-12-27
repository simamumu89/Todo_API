package com.shima.todo_list.controller.response;

public class CreateResponse {

    private String message;

    public CreateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
