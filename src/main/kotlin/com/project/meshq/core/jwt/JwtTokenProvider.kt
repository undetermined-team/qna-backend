package com.project.meshq.core.jwt

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

//Jwt Token 생성, 인증, 권한부여, 유효성검사 등
@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private var key: String,
    private val customUserDetailService: CustomUserDetailService
): TokenProvider<JwtToken> {

    override fun createAuthToken(email: String, role: String): String {
        val jwtToken = JwtToken(id = email, role = role, key = key)
        return jwtToken.createToken()
    }

    override fun getAuthentication(authToken: JwtToken): Authentication {
        val userDetails = customUserDetailService.loadUserByUsername(authToken.getClaims())
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    override fun convertAuthToken(tokenStr: String): JwtToken {
        return JwtToken(tokenStr, key)
    }

    override fun resolveToken(request: HttpServletRequest): String {
        return request.getHeader("X-AUTH-TOKEN") ?: ""
    }
}