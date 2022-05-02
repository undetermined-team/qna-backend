package com.project.meshq.application.member.adapter.`in`

import com.daou.hc.common.util.*
import com.daou.hc.member.service.MemberService
import com.daou.hc.core.controller.ApiController
import com.daou.hc.core.jwt.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
class AuthController(
    private val memberService: MemberService,
    private val jwtTokenProvider: JwtTokenProvider
) : ApiController() {

    @GetMapping("/member/sign-in")
    fun signIn(signInModel: SignInModel, response: HttpServletResponse): ResponseEntity<Void> {
        val tokenDataModel = memberService.checkPassword(
            signInModel.email,
            signInModel.password
        )

        val accessToken = jwtTokenProvider.createToken(tokenDataModel, ACCESS_TOKEN_VALID_TIME)
        response.addCookie(
            cookieGenerator(
                ACCESS_TOKEN,
                accessToken
            )
        )

        val refreshToken = jwtTokenProvider.createToken(tokenDataModel, REFRESH_TOKEN_VALID_TIME)
        response.addCookie(
            cookieGenerator(
                REFRESH_TOKEN,
                refreshToken
            )
        )

        memberService.updateRefreshToken(tokenDataModel.id, refreshToken)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/member/sign-up")
    fun signUp(@RequestBody @Valid signUpModel: SignUpModel): ResponseEntity<Long> {
        val memberId = memberService.createMember(signUpModel)

        return ResponseEntity.ok(memberId)
    }

    @GetMapping("/refresh/access-token")
    fun refreshAccessToken(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<Void> {
        val refreshToken = jwtTokenProvider.resolve(request)
        val tokenDataModel: TokenDataModel = memberService.compareRefreshToken(memberId(), refreshToken)

        val accessToken = jwtTokenProvider.createToken(tokenDataModel, ACCESS_TOKEN_VALID_TIME)
        response.addCookie(cookieGenerator(ACCESS_TOKEN, accessToken))

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/member/test")
    fun tokenTest(): String {
        println(memberId())
        println(memberRole())
        return "token test"
    }

}


