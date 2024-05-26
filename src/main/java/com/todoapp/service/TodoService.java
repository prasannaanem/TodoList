package com.todoapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.entity.ToDo;
import com.todoapp.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
    private TodoRepository todoRepository;

    public List<ToDo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<ToDo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public ToDo save(ToDo todo) {
        todo.setUpdatedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
