package com.teamsparta.todoserver.user.loginUser.service

import com.teamsparta.todoserver.user.loginUser.entity.LoginUser
import com.teamsparta.todoserver.user.loginUser.repository.LoginUserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class LoginUserService(
    private val loginUserRepository: LoginUserRepository
) {
    fun login(loginId: String, token: String): Boolean{
        if(loginUserRepository.existsByLoginId(loginId)){
            loginUserRepository.findByLoginId(loginId)?.token=token
        }
        else{
            loginUserRepository.save(
                LoginUser(
                    loginId = loginId,
                    token = token
                )
            )
        }
        return true
    }

    fun logout(loginId: String):Boolean {
        loginUserRepository.deleteByLoginId(loginId)
        return true
    }
}