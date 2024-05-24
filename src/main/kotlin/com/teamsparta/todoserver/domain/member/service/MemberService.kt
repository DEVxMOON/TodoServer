package com.teamsparta.todoserver.domain.member.service

import com.teamsparta.todoserver.domain.member.dto.LoginRequest
import com.teamsparta.todoserver.domain.member.dto.MemberRequest
import com.teamsparta.todoserver.domain.member.dto.MemberResponse

interface MemberService{
    fun signUp(memberRequest: MemberRequest): MemberResponse
    fun login(loginRequest: LoginRequest): String
}