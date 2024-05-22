package com.teamsparta.todoserver.domain.member.dto

data class MemberRequest (
    var id:Long?,
    val loginId:String,
    val name:String,
    val password:String,
)