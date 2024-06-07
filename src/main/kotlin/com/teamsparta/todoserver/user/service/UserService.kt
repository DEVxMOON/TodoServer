package com.teamsparta.todoserver.user.service

import com.teamsparta.todoserver.infra.security.jwt.JwtUtil
import com.teamsparta.todoserver.user.dto.*
import com.teamsparta.todoserver.user.entity.User
import com.teamsparta.todoserver.user.entity.toResponse
import com.teamsparta.todoserver.user.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
){
    @Transactional
    fun signUp(signUpRequest: SignUpRequest): UserResponse {
        val member = signUpRequest.toEntity(signUpRequest.password)
        userRepository.save(member)
        return member.toResponse()
    }

    fun login(loginRequest: LoginRequest): String {
        val token = userRepository.findUserByLoginId(loginRequest.loginId)
            ?.let { user ->
                jwtUtil.generateAccessToken("loginId", user.loginId)
            } ?: throw EntityNotFoundException("Member not found")
        return token
    }

    @Transactional
    fun getUserInfo(request: GetUserInfoRequest): UserResponse {
        return validateLoginIdFromToken(request.token).toResponse()
    }

    private fun validateLoginIdFromToken(token: String): User {
        return jwtUtil.validateToken(token).let {
            userRepository.findUserByLoginId(it)
                ?: throw EntityNotFoundException("User Not Found")
        }
    }
}