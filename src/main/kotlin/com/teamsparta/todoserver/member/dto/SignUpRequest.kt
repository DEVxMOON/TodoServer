package com.teamsparta.todoserver.member.dto

import com.teamsparta.todoserver.member.entity.Member

data class SignUpRequest (
    var id:Long?,
    val loginId:String,
    val name:String,
    val password:String,
)

fun SignUpRequest.toEntity(password:String): Member {
    return Member(
        loginId = loginId,
        password = password,
        name=name
    )
}