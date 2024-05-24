package com.teamsparta.todoserver.domain.todo.dto

data class CreateCommentRequest (
    val name:String,
    val body:String,
    val password:String,
)