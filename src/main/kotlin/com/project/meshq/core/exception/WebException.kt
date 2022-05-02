package com.daou.hc.common.exception

import org.springframework.http.HttpStatus
import org.springframework.util.ObjectUtils

class WebException(
    exceptionDefinition: ExceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR,
    message: String = ""
) :
    RuntimeException() {

    val httpStatus: HttpStatus = exceptionDefinition.httpStatus
    val clientMsg: String = if (ObjectUtils.isEmpty(message)) exceptionDefinition.message else message
    val serverMsg: String = exceptionDefinition.code
}