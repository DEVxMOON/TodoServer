package com.teamsparta.todoserver.todo.controller

import com.teamsparta.todoserver.member.dto.GetMemberInfoRequest
import com.teamsparta.todoserver.todo.dto.CommentResponse
import com.teamsparta.todoserver.todo.dto.CommentRequest
import com.teamsparta.todoserver.todo.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun createComment(
        @PathVariable todoId:Long,
        @RequestBody commentRequest: CommentRequest,
        @RequestBody getMemberInfoRequest: GetMemberInfoRequest
    ): ResponseEntity<CommentResponse> {
        return commentService.validateToken(getMemberInfoRequest.token).let{
            ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.createComment(todoId, commentRequest,it.name))
        }
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId:Long,
        @PathVariable commentId:Long,
        @RequestBody commentRequest: CommentRequest,
        @RequestBody getMemberInfoRequest: GetMemberInfoRequest
    ): ResponseEntity<CommentResponse> {
        return commentService.checkOwner(getMemberInfoRequest.token,todoId,commentId).let{
            ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(todoId, commentId, commentRequest))
        }
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId:Long,
        @PathVariable commentId: Long,
        @RequestBody getMemberInfoRequest: GetMemberInfoRequest
    ): ResponseEntity<Unit> {
        return commentService.checkOwner(getMemberInfoRequest.token,todoId,commentId).let{
            ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(commentService.deleteComment(todoId,commentId))
        }
    }
}