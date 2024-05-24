package com.teamsparta.todoserver.todo.service

import com.teamsparta.todoserver.domain.todo.dto.CommentResponse
import com.teamsparta.todoserver.domain.todo.dto.CreateCommentRequest
import com.teamsparta.todoserver.domain.todo.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(todoId:Long,createCommentRequest: CreateCommentRequest) : CommentResponse
    fun updateComment(todoId:Long, commentId:Long, updateCommentRequest: UpdateCommentRequest, name:String, password:String) : CommentResponse
    fun deleteComment(todoId:Long,commentId:Long,name:String,password:String)
}