package com.daou.hc.common.exception

import com.daou.hc.common.util.log
import com.daou.hc.core.domain.model.ResponseErrorModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.io.IOException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleWebException1(e: MethodArgumentNotValidException): ResponseEntity<ResponseErrorModel> {
        log().error(e.stackTraceToString())
        val fieldErrors = e.bindingResult.fieldErrors
        val code = fieldErrors[0].codes?.get(0)
        val message = fieldErrors[0].defaultMessage

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ResponseErrorModel(HttpStatus.BAD_REQUEST, code, message))
    }

    @ExceptionHandler(value = [WebException::class])
    fun handleWebException(e: WebException): ResponseEntity<ResponseErrorModel> {
        log().error(e.serverMsg)
        log().error(e.stackTraceToString())
        return ResponseEntity.status(e.httpStatus)
            .body(ResponseErrorModel(e.httpStatus, e.serverMsg, e.clientMsg))
    }

    @ExceptionHandler(value = [RuntimeException::class, IOException::class])
    fun handleGlobalException(e: Exception): ResponseEntity<ResponseErrorModel> {
        log().error(e.stackTraceToString())
        log().error(ExceptionCode.INTERNAL_SERVER_ERROR.code)
        RuntimeException().printStackTrace()
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, e.message, e.message))
    }

}