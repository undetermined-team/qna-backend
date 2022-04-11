package com.project.meshq.core.jwt

import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface TokenProvider<T> {
    fun createAuthToken(id: String, role: String): String
    fun convertAuthToken(tokenStr: String): T
    fun getAuthentication(authToken: T): Authentication
    fun resolveToken(request: HttpServletRequest): String
}