package com.teamsparta.todoserver.todo.dto

import com.teamsparta.todoserver.user.dto.GetUserInfoRequest

data class CommentRequest (
    val member: GetUserInfoRequest,
    val body:String,
)