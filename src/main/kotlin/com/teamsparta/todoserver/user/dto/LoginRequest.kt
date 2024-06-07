package com.teamsparta.todoserver.user.dto

data class LoginRequest (
    val loginId:String,
    val password:String,
)