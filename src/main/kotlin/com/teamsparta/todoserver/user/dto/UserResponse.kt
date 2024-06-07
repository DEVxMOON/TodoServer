package com.teamsparta.todoserver.user.dto

data class UserResponse (
    var id:Long,
    val loginId:String,
    val name:String
)