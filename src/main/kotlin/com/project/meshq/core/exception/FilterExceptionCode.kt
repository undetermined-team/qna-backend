package com.daou.hc.common.exception

import org.springframework.http.HttpStatus

data class FilterExceptionCode(
    val status: HttpStatus,
    val errorCode: String,
    val message: String,
)