package com.alevel.todo.todolist.controller;

import com.alevel.todo.todolist.entity.Todo;
import com.alevel.todo.todolist.entity.request.SaveTodoRequest;
import com.alevel.todo.todolist.service.TodoCRUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoCRUID todoCRUID;

    public TodoController(TodoCRUID todoCRUID) {
        this.todoCRUID = todoCRUID;
    }



    @GetMapping
    private List<Todo> getNotDoneAll(@RequestParam(required = false) Boolean done) {
        return done != null
                ?todoCRUID.getTodoListNotDone(done)
                :todoCRUID.getTodoList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Todo create(@RequestBody SaveTodoRequest request) {
        return todoCRUID.create(request);
    }

    @GetMapping("/{id}")
    private Todo get(@PathVariable Long id) {
        return todoCRUID.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    private void update(@PathVariable Long id, @RequestBody SaveTodoRequest request) {
        todoCRUID.update(id, request);
    }

    @DeleteMapping("/{id}")
    private Todo delete(@PathVariable Long id) {
        return todoCRUID.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
