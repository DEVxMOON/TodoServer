package com.teamsparta.todoserver.domain.member.controller

import com.teamsparta.todoserver.domain.member.dto.MemberRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/member")
@RestController
class MemberController {

    @PostMapping("/signup")
    fun signUp(@RequestBody memberRequest: MemberRequest){
        TODO()
    }

    @PostMapping("/login")
    fun login(@RequestBody memberRequest: MemberRequest){
        TODO()
    }
}