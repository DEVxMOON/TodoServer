package com.teamsparta.todoserver.domain.todo.repository

import com.teamsparta.todoserver.domain.todo.model.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TodoRepository : JpaRepository<Todo, Long> {
    @EntityGraph(attributePaths = ["comments"])
    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.comments")
    fun findAllWithComments(pageable:Pageable): Page<Todo>
}