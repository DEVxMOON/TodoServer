package com.teamsparta.todoserver.member.service

import com.teamsparta.todoserver.infra.security.jwt.JwtUtil
import com.teamsparta.todoserver.member.dto.LoginRequest
import com.teamsparta.todoserver.member.dto.SignUpRequest
import com.teamsparta.todoserver.member.dto.MemberResponse
import com.teamsparta.todoserver.member.dto.toEntity
import com.teamsparta.todoserver.member.entity.toResponse
import com.teamsparta.todoserver.member.repository.MemberRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val jwtUtil: JwtUtil,
) : MemberService  {

    @Transactional
    override fun signUp(memberRequest: SignUpRequest): MemberResponse {
        val member = memberRequest.toEntity(memberRequest.password)
        memberRepository.save(member)
        return member.toResponse()
    }

    override fun login(loginRequest: LoginRequest): String {
        val token = memberRepository.findMemberByLoginId(loginRequest.loginId)
            ?.let{member ->
                jwtUtil.generateAccessToken("loginId", member.loginId)
            } ?: throw EntityNotFoundException("Member not found")
        return token
    }
}