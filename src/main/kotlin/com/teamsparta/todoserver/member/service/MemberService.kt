package com.teamsparta.todoserver.member.service

import com.teamsparta.todoserver.member.dto.LoginRequest
import com.teamsparta.todoserver.member.dto.MemberRequest
import com.teamsparta.todoserver.member.dto.MemberResponse

interface MemberService{
    fun signUp(memberRequest: com.teamsparta.todoserver.member.dto.MemberRequest): com.teamsparta.todoserver.member.dto.MemberResponse
    fun login(loginRequest: com.teamsparta.todoserver.member.dto.LoginRequest): String
}