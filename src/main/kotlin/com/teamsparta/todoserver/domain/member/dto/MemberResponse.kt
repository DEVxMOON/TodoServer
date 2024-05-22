package com.teamsparta.todoserver.domain.member.dto

data class MemberResponse (
    var id:Long,
    val loginId:String,
    val name:String
)