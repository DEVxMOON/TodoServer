package com.teamsparta.todoserver.member.dto

data class MemberResponse (
    var id:Long,
    val loginId:String,
    val name:String
)