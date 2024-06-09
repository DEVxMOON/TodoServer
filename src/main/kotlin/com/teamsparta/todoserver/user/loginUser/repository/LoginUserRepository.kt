package com.teamsparta.todoserver.user.loginUser.repository

import com.teamsparta.todoserver.user.loginUser.entity.LoginUser
import org.springframework.data.jpa.repository.JpaRepository

interface LoginUserRepository: JpaRepository<LoginUser, Long>{
    fun findByLoginId(loginId: String): LoginUser?
    fun deleteByLoginId(loginId: String)
    fun existsByLoginId(loginId: String):Boolean
}