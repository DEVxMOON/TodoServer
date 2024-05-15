package com.teamsparta.todoserver.domain.comment.controller

import com.teamsparta.todoserver.domain.comment.dto.CommentResponse
import com.teamsparta.todoserver.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todoserver.domain.comment.dto.UpdateCommentRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController {

    @PostMapping
    fun createComment(@PathVariable todoId:Long, @RequestBody createComment: CreateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()

    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable todoId:Long,@PathVariable commentId:Long, name:String,password:String, @RequestBody updateComment: UpdateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable todoId:Long,@PathVariable commentId: Long,name:String,password:String): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}