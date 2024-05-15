package com.teamsparta.todoserver.domain.comment.service

import com.teamsparta.todoserver.domain.comment.dto.CommentResponse
import com.teamsparta.todoserver.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todoserver.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(todoId:Long,createCommentRequest: CreateCommentRequest) : CommentResponse
    fun updateComment(todoId:Long, commentId:Long, updateCommentRequest: UpdateCommentRequest, name:String, password:String) : CommentResponse
    fun deleteComment(todoId:Long,commentId:Long,name:String,password:String)
}