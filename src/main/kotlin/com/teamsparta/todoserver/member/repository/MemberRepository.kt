package com.teamsparta.todoserver.member.repository

import com.teamsparta.todoserver.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository :JpaRepository<Member,Long>{
    fun findMemberByLoginId(loginId : String) : Member?
}