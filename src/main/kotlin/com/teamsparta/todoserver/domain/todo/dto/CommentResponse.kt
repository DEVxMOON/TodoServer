package com.teamsparta.todoserver.domain.todo.dto

data class CommentResponse (
    val name:String,
    val body:String,
    val id:Long,
)