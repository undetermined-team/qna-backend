package com.daou.hc.common.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
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

        outputStream(response, (exception ?: ExceptionCode.AUTHENTICATION_FAIL) as ExceptionCode)
    }

    private fun outputStream(
        response: HttpServletResponse,
        exception: ExceptionCode
    ) {
        response.contentType = "application/json";
        response.status = HttpServletResponse.SC_UNAUTHORIZED;

        ObjectMapper().writeValue(
            response.outputStream,
            FilterExceptionCode(
                exception.httpStatus,
                exception.code,
                exception.message
            )
        )
    }
}