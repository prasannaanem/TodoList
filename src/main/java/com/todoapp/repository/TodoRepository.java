package com.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.entity.ToDo;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {
	
	

}
