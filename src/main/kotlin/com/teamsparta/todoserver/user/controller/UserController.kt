package com.teamsparta.todoserver.user.controller

import com.teamsparta.todoserver.user.dto.GetUserInfoRequest
import com.teamsparta.todoserver.user.dto.LoginRequest
import com.teamsparta.todoserver.user.dto.SignUpRequest
import com.teamsparta.todoserver.user.dto.UserResponse
import com.teamsparta.todoserver.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/member")
@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest):ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequest))
    }
    @PostMapping("/userinfo")
    fun getUserInfo(@RequestBody getUserInfoRequest: GetUserInfoRequest)
            : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUserInfo(getUserInfoRequest))
    }

    @DeleteMapping("/logout")
    fun logout(@RequestBody deleteRequest:GetUserInfoRequest):ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.logout(deleteRequest))
    }
}