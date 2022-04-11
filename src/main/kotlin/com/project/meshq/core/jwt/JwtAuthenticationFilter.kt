package com.project.meshq.core.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {

    //jwt token 의 유효성 검증
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenStr = jwtTokenProvider.resolveToken(request)
        if(tokenStr.isEmpty()) {
            filterChain.doFilter(request, response)
            return
        }

        val token = jwtTokenProvider.convertAuthToken(tokenStr)

        if (token.validate()) {
            val authentication: Authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication;
        }
        filterChain.doFilter(request, response)
    }
}