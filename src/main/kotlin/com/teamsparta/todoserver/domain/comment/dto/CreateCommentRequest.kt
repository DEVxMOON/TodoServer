package com.teamsparta.todoserver.domain.comment.dto

data class CreateCommentRequest (
    val name:String,
    val body:String,
    val password:String,
)