package com.teamsparta.todoserver.user.dto

import com.teamsparta.todoserver.user.entity.User

data class SignUpRequest (
    var id:Long?,
    val loginId:String,
    val name:String,
    val password:String,
)

fun SignUpRequest.toEntity(password:String): User {
    return User(
        loginId = loginId,
        password = password,
        name=name
    )
}