package com.teamsparta.todoserver.member.controller

import com.teamsparta.todoserver.member.dto.GetMemberInfoRequest
import com.teamsparta.todoserver.member.dto.LoginRequest
import com.teamsparta.todoserver.member.dto.SignUpRequest
import com.teamsparta.todoserver.member.dto.MemberResponse
import com.teamsparta.todoserver.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/member")
@RestController
class MemberController(private val memberService: MemberService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest):ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }
    @PostMapping("/userinfo")
    fun getUserInfo(@RequestBody getMemberInfoRequest: GetMemberInfoRequest)
            : ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.getUserInfo(getMemberInfoRequest))
    }
}