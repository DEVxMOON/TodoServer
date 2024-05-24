package com.teamsparta.todoserver.todo.dto

import java.time.LocalDateTime

data class TodoResponse (
    val id:Long,
    val title:String,
    val author:String,
    val date:LocalDateTime,
    val body:String,
    val done:Boolean,
    val comments:List<CommentResponse>
)