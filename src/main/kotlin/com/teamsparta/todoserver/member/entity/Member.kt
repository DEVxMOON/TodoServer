package com.teamsparta.todoserver.member.entity

import com.teamsparta.todoserver.member.dto.MemberResponse
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member (
    @Column(name="login_id",nullable = false)
    var loginId: String,

    @Column(name="password",nullable = false)
    var password: String,

    @Column(name="name",nullable = false)
    var name: String
    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun com.teamsparta.todoserver.member.entity.Member.toResponse(): com.teamsparta.todoserver.member.dto.MemberResponse {
    return com.teamsparta.todoserver.member.dto.MemberResponse(
        id = id!!,
        loginId = loginId,
        name = name,
    )
}