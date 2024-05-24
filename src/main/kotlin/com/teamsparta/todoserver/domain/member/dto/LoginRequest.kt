package com.teamsparta.todoserver.domain.member.dto

data class LoginRequest (
    val loginId:String,
    val password:String,
)