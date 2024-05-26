package com.todoapp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todoapp.entity.ToDo;
import com.todoapp.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
	
	
	 @Autowired
	    private TodoService todoService;

	    @GetMapping
	    public String listTodos(Model model) {
	        model.addAttribute("todos", todoService.findAll());
	        return "todo-list";
	    }

	    @GetMapping("/new")
	    public String showCreateForm(Model model) {
	        model.addAttribute("todo", new ToDo());
	        return "todo-form";
	    }

	    @PostMapping
	    public String saveTodo(@ModelAttribute("todo") ToDo todo) {
	        todo.setCreatedAt(LocalDateTime.now());
	        todo.setUpdatedAt(LocalDateTime.now());
	        todoService.save(todo);
	        return "redirect:/todos";
	    }

	    @GetMapping("/edit/{id}")
	    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    	ToDo todo = todoService.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
	        model.addAttribute("todo", todo);
	        return "todo-form";
	    }

	    @PostMapping("/update/{id}")
	    public String updateTodo(@PathVariable("id") Long id, @ModelAttribute("todo") ToDo todo) {
	        todo.setUpdatedAt(LocalDateTime.now());
	        todoService.save(todo);
	        return "redirect:/todos";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteTodo(@PathVariable("id") Long id) {
	        todoService.deleteById(id);
	        return "redirect:/todos";
	    }

}
