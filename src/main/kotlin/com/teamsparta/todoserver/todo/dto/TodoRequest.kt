package com.teamsparta.todoserver.todo.dto

import com.teamsparta.todoserver.member.dto.GetMemberInfoRequest
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class TodoRequest(
    @field:NotBlank(message = "Title must not be blank")
    @field:Size(min = 1, max = 200, message = "Title must between 1 and 200")
    val title: String,
    @field:NotBlank(message = "Body must not be blank")
    @field:Size(min = 1, max = 1000, message = "Body must between 1 and 1000")
    val body: String,
    val date: LocalDateTime,
    val member: GetMemberInfoRequest,
)