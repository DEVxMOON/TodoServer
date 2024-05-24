package com.teamsparta.todoserver.member.controller

import com.teamsparta.todoserver.member.dto.LoginRequest
import com.teamsparta.todoserver.member.dto.MemberRequest
import com.teamsparta.todoserver.member.dto.MemberResponse
import com.teamsparta.todoserver.domain.member.service.MemberServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/member")
@RestController
class MemberController(private val memberService: MemberServiceImpl) {

    @PostMapping("/signup")
    fun signUp(@RequestBody memberRequest: com.teamsparta.todoserver.member.dto.MemberRequest):ResponseEntity<com.teamsparta.todoserver.member.dto.MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signUp(memberRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: com.teamsparta.todoserver.member.dto.LoginRequest): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }
}