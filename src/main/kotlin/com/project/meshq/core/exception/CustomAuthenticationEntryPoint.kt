package com.project.meshq.core.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val exception: Any? = request.getAttribute("exception")

        outputStream(response)
    }

    private fun outputStream(
        response: HttpServletResponse,
    ) {
        response.contentType = "application/json";
        response.status = HttpServletResponse.SC_UNAUTHORIZED;

        ObjectMapper().writeValue(
            response.outputStream,
            RuntimeException()
        )
    }
}