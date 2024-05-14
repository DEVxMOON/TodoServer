package com.teamsparta.todoserver.domain.todo.dto

import java.time.LocalDate

data class TodoResponse (
    val id:Long,
    val title:String,
    val author:String,
    val date:LocalDate,
    val body:String,
)