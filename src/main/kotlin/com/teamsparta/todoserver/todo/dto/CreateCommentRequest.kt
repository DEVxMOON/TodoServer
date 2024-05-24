package com.teamsparta.todoserver.todo.dto

data class CreateCommentRequest (
    val name:String,
    val body:String,
    val password:String,
)