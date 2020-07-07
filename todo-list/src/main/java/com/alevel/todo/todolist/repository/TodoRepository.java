package com.alevel.todo.todolist.repository;

import com.alevel.todo.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<Todo, Long> {



}
