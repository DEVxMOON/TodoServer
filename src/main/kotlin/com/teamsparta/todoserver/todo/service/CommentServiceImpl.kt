package com.teamsparta.todoserver.todo.service

import com.teamsparta.todoserver.domain.todo.dto.CommentResponse
import com.teamsparta.todoserver.domain.todo.dto.CreateCommentRequest
import com.teamsparta.todoserver.domain.todo.dto.UpdateCommentRequest
import com.teamsparta.todoserver.domain.todo.entity.Comment
import com.teamsparta.todoserver.domain.todo.entity.toResponse
import com.teamsparta.todoserver.domain.todo.repository.CommentRepository
import com.teamsparta.todoserver.exception.ModelNotFoundException
import com.teamsparta.todoserver.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
) : CommentService {
    override fun createComment(todoId: Long, createCommentRequest: CreateCommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = Comment(
            name = createCommentRequest.name,
            body = createCommentRequest.body,
            password = createCommentRequest.password,
            todo = todo
        )
        todo.addComment(comment)
        commentRepository.save(comment)

        return comment.toResponse()
    }

    override fun updateComment(
        todoId: Long,
        commentId: Long,
        updateCommentRequest: UpdateCommentRequest,
        name: String,
        password: String
    ): CommentResponse {
        val comment =
            commentRepository.findByTodoIdAndId(todoId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (password != comment.password || name != comment.name) {
            throw IllegalArgumentException("Passwords don't match")
        }

        val (body) = updateCommentRequest
        comment.body = body

        return commentRepository.save(comment).toResponse()
    }

    override fun deleteComment(todoId: Long, commentId: Long, name: String, password: String) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (password != comment.password || name != comment.name) {
            throw IllegalArgumentException("Passwords don't match")
        }

        todo.deleteComment(comment)
        commentRepository.delete(comment)
    }

}