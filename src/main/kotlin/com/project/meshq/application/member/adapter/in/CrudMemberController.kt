package com.project.meshq.application.member.adapter.`in`

import com.project.meshq.application.member.application.port.`in`.CrudMemberUseCase
import com.project.meshq.application.member.domain.Member
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
class CrudMemberController(val crudMemberUseCase: CrudMemberUseCase) {

    @ApiOperation("회원가입")
    @PostMapping("/signup")
    fun signUp(@RequestBody memberSignUp: MemberSignUp): ResponseEntity<Long>{

        val id = crudMemberUseCase.signUp(memberSignUp.toDomain())

        return ResponseEntity.ok().body(id)
    }

    data class MemberSignUp(
        val email: String,
        val name: String,
        val pwd: String
    ) {
        fun toDomain(): Member {
            return Member(email = email, name = name, pwd = pwd)
        }
    }
}