package com.teamsparta.todoserver.domain.todo.dto

import com.teamsparta.todoserver.domain.comment.dto.CommentResponse
import java.time.LocalDate

data class TodoResponse (
    val id:Long,
    val title:String,
    val author:String,
    val date:LocalDate,
    val body:String,
    val comments:List<CommentResponse>
)