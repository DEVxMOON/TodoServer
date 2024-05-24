package com.teamsparta.todoserver.member.dto

data class MemberRequest (
    var id:Long?,
    val loginId:String,
    val name:String,
    val password:String,
)