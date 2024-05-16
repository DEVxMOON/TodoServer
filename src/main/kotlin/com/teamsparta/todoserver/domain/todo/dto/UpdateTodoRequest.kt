package com.teamsparta.todoserver.domain.todo.dto

import java.time.LocalDate

data class UpdateTodoRequest (
    val title: String,
    val author: String,
    val body: String,
    val date: LocalDate,
)