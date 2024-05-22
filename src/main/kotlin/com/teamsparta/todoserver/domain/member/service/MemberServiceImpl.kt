package com.teamsparta.todoserver.domain.member.service

import com.teamsparta.todoserver.domain.member.dto.MemberRequest
import com.teamsparta.todoserver.domain.member.dto.MemberResponse
import com.teamsparta.todoserver.domain.member.entity.Member
import com.teamsparta.todoserver.domain.member.entity.toResponse
import com.teamsparta.todoserver.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(private val memberRepository : MemberRepository) : MemberService  {
    override fun signUp(memberRequest: MemberRequest): MemberResponse {

        //TODO : 조건 조금 더 고민, 아이디가 핵심? 작성자 이름 기준으로 댓글, TODO를 작성한다고 하면. HOW?
        if(memberRepository.findMemberByName(memberRequest.name) != null
            && memberRepository.findMemberByLoginId(memberRequest.loginId) != null){
            throw IllegalArgumentException("LoginId Already Exist")
        }

        //TODO : Password 암호화를 해야할 것 같은데... signUp을 할 때, token을 발행해서 넣어줘야할 것 같다.
        return memberRepository.save(
            Member(
                loginId = memberRequest.loginId,
                password = memberRequest.password,
                name = memberRequest.name,
            )
        ).toResponse()
    }
}