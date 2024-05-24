package com.teamsparta.todoserver.member.repository

import com.teamsparta.todoserver.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository :JpaRepository<com.teamsparta.todoserver.member.entity.Member,Long>{
    fun findMemberByName(memberName : String) : com.teamsparta.todoserver.member.entity.Member?
    fun findMemberByLoginId(loginId : String) : com.teamsparta.todoserver.member.entity.Member?
}