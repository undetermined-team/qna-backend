package com.project.meshq.application.member.adapter.`in`

import com.project.meshq.application.member.application.port.`in`.CrudMemberUseCase
import com.project.meshq.application.member.domain.Member
import com.project.meshq.application.member.domain.Role
import com.project.meshq.application.util.ExceptionType
import com.project.meshq.application.util.throwCheck
import com.project.meshq.core.jwt.JwtTokenProvider
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Api(tags = ["회원관련 API"], description = "회원 CRUD")
@RestController
class AuthMemberController(
    val crudMemberUseCase: CrudMemberUseCase,
    val jwtTokenProvider: JwtTokenProvider
) {

    @ApiOperation("회원가입")
    @PostMapping("/signup")
    fun signUp(@RequestBody memberSignUp: MemberSignUp): ResponseEntity<Long> {
        val id = crudMemberUseCase.signUp(memberSignUp)

        return ResponseEntity.ok().body(id)
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    fun signUp(@RequestBody memberLogin: MemberLogin): ResponseEntity<String> {
        val member = crudMemberUseCase.login(memberLogin)

        return ResponseEntity.ok().body(
            jwtTokenProvider.createAuthToken(member.email, member.role.code))
    }

    @ApiOperation("토큰 테스트")
    @GetMapping("/member/testA")
    fun testA(principal: Principal): ResponseEntity<String> {
        println(principal.name)
        return ResponseEntity.ok().body("token test")
    }
}

data class MemberSignUp(
    val email: String,
    val name: String,
    val pwd: String
) {
    fun toDomain(): Member {
        return Member(email, name, pwd)
    }
}

data class MemberLogin(
    val email: String,
    val pwd: String
) {
    fun toDomain(): Member {
        return Member(email = email, pwd = pwd)
    }
}

data class ResponseMember(
    val email: String,
    val role: Role
)