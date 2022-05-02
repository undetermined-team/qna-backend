package com.daou.hc.common.exception

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler: AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.contentType = "application/json";
        response.status = HttpServletResponse.SC_FORBIDDEN;
        ObjectMapper().writeValue(
            response.outputStream,
            FilterExceptionCode(
                HttpStatus.FORBIDDEN,
                "403",
                "접근 권한이 없습니다"
            )
        )
    }
}