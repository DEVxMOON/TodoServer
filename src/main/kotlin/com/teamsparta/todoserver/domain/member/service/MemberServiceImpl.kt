package com.teamsparta.todoserver.domain.member.service

import com.teamsparta.todoserver.domain.member.dto.MemberRequest
import com.teamsparta.todoserver.domain.member.dto.MemberResponse
import com.teamsparta.todoserver.domain.member.entity.Member
import com.teamsparta.todoserver.domain.member.entity.toResponse
import com.teamsparta.todoserver.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(private val memberRepository : MemberRepository) : MemberService  {
    override fun signUp(memberRequest: MemberRequest): MemberResponse {
        //TODO : DB에 아이디, 이름이 동일한 사람이 있는지 확인
        return memberRepository.save(
            Member(
                loginId = memberRequest.loginId,
                password = memberRequest.password,
                name = memberRequest.name,
            )
        ).toResponse()
    }
}