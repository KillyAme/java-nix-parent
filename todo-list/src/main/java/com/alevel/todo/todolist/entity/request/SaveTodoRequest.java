package com.alevel.todo.todolist.entity.request;

public class SaveTodoRequest {

    private String text;

    private Boolean done;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
