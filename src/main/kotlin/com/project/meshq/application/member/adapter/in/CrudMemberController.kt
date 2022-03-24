package com.project.meshq.application.member.adapter.`in`

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["회원관련 API"], description = "회원 CRUD")
@RequestMapping("/member")
@RestController
class CrudMemberController {

    @ApiOperation("회원가입")
    @PostMapping("/signup")
    fun signUp(@RequestBody memberSignUp: MemberSignUp): ResponseEntity<MemberSignUp>{
        println(memberSignUp)
        return ResponseEntity.ok().body(memberSignUp)
    }

    data class MemberSignUp(
        val email: String,
        val name: String
    )
}