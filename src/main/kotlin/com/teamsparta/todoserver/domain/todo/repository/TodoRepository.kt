package com.teamsparta.todoserver.domain.todo.repository

import com.teamsparta.todoserver.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

}