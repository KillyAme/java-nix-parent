package com.alevel.todo.todolist.service;

import com.alevel.todo.todolist.entity.Todo;
import com.alevel.todo.todolist.entity.request.SaveTodoRequest;

import java.util.List;
import java.util.Optional;

public interface TodoCRUID {

    List<Todo> getTodoList();

    List<Todo> getTodoListNotDone(Boolean done);

    Todo create(SaveTodoRequest request);

    Optional<Todo> getById(Long id);

    void update(Long id, SaveTodoRequest request);

    Optional<Todo> deleteById(Long id);





}
