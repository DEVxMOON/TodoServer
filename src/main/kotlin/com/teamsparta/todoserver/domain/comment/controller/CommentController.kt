package com.teamsparta.todoserver.domain.comment.controller

import com.teamsparta.todoserver.domain.comment.dto.CommentResponse
import com.teamsparta.todoserver.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todoserver.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todoserver.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun createComment(@PathVariable todoId:Long, @RequestBody createComment: CreateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId, createComment))

    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable todoId:Long,@PathVariable commentId:Long, name:String,password:String, @RequestBody updateComment: UpdateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateComment,name,password))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable todoId:Long,@PathVariable commentId: Long,name:String,password:String): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(todoId,commentId,name,password))
    }
}