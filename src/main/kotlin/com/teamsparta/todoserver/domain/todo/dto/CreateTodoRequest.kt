package com.teamsparta.todoserver.domain.todo.dto

import java.time.LocalDateTime

data class CreateTodoRequest (
    val title: String,
    val author:String,
    val body: String,
    val date: LocalDateTime,
)