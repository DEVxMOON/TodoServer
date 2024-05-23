package com.teamsparta.todoserver.domain.member.service

import com.teamsparta.todoserver.domain.member.dto.MemberRequest
import com.teamsparta.todoserver.domain.member.dto.MemberResponse
import com.teamsparta.todoserver.domain.member.repository.MemberRepository

interface MemberService{
    fun signUp(memberRequest: MemberRequest): MemberResponse
    fun login(memberRequest: MemberRequest): MemberResponse
}