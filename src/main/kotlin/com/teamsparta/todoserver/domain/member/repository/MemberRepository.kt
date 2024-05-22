package com.teamsparta.todoserver.domain.member.repository

import com.teamsparta.todoserver.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository :JpaRepository<Member,Long>{
}