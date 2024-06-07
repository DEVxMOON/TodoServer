package com.teamsparta.todoserver.user.repository

import com.teamsparta.todoserver.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository :JpaRepository<User,Long>{
    fun findUserByLoginId(loginId : String) : User?
}