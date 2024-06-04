package com.teamsparta.todoserver.member.service

interface MemberService{
    fun signUp(memberRequest: com.teamsparta.todoserver.member.dto.SignUpRequest): com.teamsparta.todoserver.member.dto.MemberResponse
    fun login(loginRequest: com.teamsparta.todoserver.member.dto.LoginRequest): String
}