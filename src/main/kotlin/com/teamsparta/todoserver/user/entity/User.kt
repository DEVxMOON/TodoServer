package com.teamsparta.todoserver.user.entity

import com.teamsparta.todoserver.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class User (
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

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        loginId = loginId,
        name = name,
    )
}