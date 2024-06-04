package com.teamsparta.todoserver.todo.repository

import com.teamsparta.todoserver.todo.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository  : JpaRepository<Comment, Long> {
    fun findByTodoIdAndId(todoId: Long, commentId: Long): Comment?
}