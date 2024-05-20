package com.teamsparta.todoserver.domain.todo.repository

import com.teamsparta.todoserver.domain.todo.model.Todo
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TodoRepository : JpaRepository<Todo, Long> {
    @EntityGraph(attributePaths = ["comments"])
    @Query("SELECT t FROM Todo t")
    fun findAllWithComments():List<Todo>
}