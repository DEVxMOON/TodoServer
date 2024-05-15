package com.teamsparta.todoserver.domain.comment.repository

import com.teamsparta.todoserver.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository  : JpaRepository<Comment, Long> {
    fun findByTodoIdAndId(todoId: Long, commentId: Long): Comment?
}