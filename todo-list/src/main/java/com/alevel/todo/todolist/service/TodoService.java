package com.alevel.todo.todolist.service;

import com.alevel.todo.todolist.entity.Todo;
import com.alevel.todo.todolist.entity.request.SaveTodoRequest;
import com.alevel.todo.todolist.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService implements TodoCRUID {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> getTodoListNotDone(Boolean done) {
        return todoRepository
                .findAll().stream()
                .filter((todo) -> todo.getDone().equals(done))
                .collect(Collectors.toList());
    }

    @Override
    public Todo create(SaveTodoRequest request) {
        Todo todo = new Todo();
        todo.setText(request.getText());
        todo.setDone(request.getDone());
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void update(Long id, SaveTodoRequest request) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        todo.setText(request.getText());
        todo.setDone(request.getDone());
        todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> deleteById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(todoRepository::delete);
        return todo;
    }
}
