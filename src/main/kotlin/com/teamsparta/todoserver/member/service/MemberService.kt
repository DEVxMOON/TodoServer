package com.teamsparta.todoserver.member.service

import com.teamsparta.todoserver.infra.security.jwt.JwtUtil
import com.teamsparta.todoserver.member.dto.*
import com.teamsparta.todoserver.member.entity.Member
import com.teamsparta.todoserver.member.entity.toResponse
import com.teamsparta.todoserver.member.repository.MemberRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val jwtUtil: JwtUtil,
){
    @Transactional
    fun signUp(memberRequest: SignUpRequest): MemberResponse {
        val member = memberRequest.toEntity(memberRequest.password)
        memberRepository.save(member)
        return member.toResponse()
    }

    fun login(loginRequest: LoginRequest): String {
        val token = memberRepository.findMemberByLoginId(loginRequest.loginId)
            ?.let { member ->
                jwtUtil.generateAccessToken("loginId", member.loginId)
            } ?: throw EntityNotFoundException("Member not found")
        return token
    }

    @Transactional
    fun getUserInfo(request: GetMemberInfoRequest): MemberResponse {
        return validateLoginIdFromToken(request.token).toResponse()
    }

    private fun validateLoginIdFromToken(token: String): Member {
        return jwtUtil.validateToken(token).let {
            memberRepository.findMemberByLoginId(it)
                ?: throw EntityNotFoundException("User Not Found")
        }
    }
}