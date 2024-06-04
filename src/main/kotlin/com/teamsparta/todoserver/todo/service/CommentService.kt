package com.teamsparta.todoserver.todo.service

import com.teamsparta.todoserver.exception.ModelNotFoundException
import com.teamsparta.todoserver.member.dto.GetMemberInfoRequest
import com.teamsparta.todoserver.member.dto.MemberResponse
import com.teamsparta.todoserver.member.service.MemberService
import com.teamsparta.todoserver.todo.dto.CommentResponse
import com.teamsparta.todoserver.todo.dto.CommentRequest
import com.teamsparta.todoserver.todo.entity.Comment
import com.teamsparta.todoserver.todo.entity.toResponse
import com.teamsparta.todoserver.todo.repository.CommentRepository
import com.teamsparta.todoserver.todo.repository.TodoRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
    private val memberService: MemberService
) {
    fun createComment(
        todoId: Long,
        commentRequest: CommentRequest,
        name:String,
    ): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = Comment(
            name = name,
            body = commentRequest.body,
            todo = todo
        )
        todo.addComment(comment)
        commentRepository.save(comment)

        return comment.toResponse()
    }

    fun updateComment(
        todoId: Long,
        commentId: Long,
        commentRequest: CommentRequest
    ): CommentResponse {
        val comment =
            commentRepository.findByTodoIdAndId(todoId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        comment.body = commentRequest.body
        return commentRepository.save(comment).toResponse()
    }

    fun deleteComment(todoId: Long, commentId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        todo.deleteComment(comment)
        commentRepository.delete(comment)
    }

    fun validateToken(token:String):MemberResponse{
        return memberService.getUserInfo(GetMemberInfoRequest(token))
    }

    fun checkOwner(token:String, feedId:Long, commentId:Long):Boolean{
        val comment = commentRepository.findByTodoIdAndId(feedId, commentId)
            ?: throw EntityNotFoundException("Comment not found")
        return validateToken(token).name == comment.name
    }


}