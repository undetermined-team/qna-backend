package com.daou.hc.common.exception

import org.springframework.http.HttpStatus

enum class ExceptionCode(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String,
) {

    // basic
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "정의되지 않은 서버 에러", "정의되지 않은 서버 에러"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청", "잘못된 요청"),
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "데이터가 존재하지 않습니다.", "데이터가 존재하지 않습니다."),
    INVALID_ARGUMENT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 입력값을 입력하였습니다.", "잘못된 입력값을 입력하였습니다."),
    UNSUPPORTED_MIMETYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "지원하지 않는 mime type 입니다.", "지원하지 않는 mime type 입니다."),
    FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "파일 처리 중 에러가 발생했습니다.", "파일 처리 중 에러가 발생했습니다."),
    NULL_POINTER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "오브젝트 Null", "오브젝트 Null"),
    PASSWORD_MATCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "패스워드가 잘못되었습니다", "패스워드가 잘못되었습니다"),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 계정입니다.", "존재하지 않는 계정입니다."),
    PROVIDER_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Provider 를 찾을 수 없습니다", "Provider 를 찾을 수 없습니다"),
    TIME_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "T001", "Token 유효시간이 만료 되었습니다"),
    WRONG_TOKEN(HttpStatus.UNAUTHORIZED, "T002", "잘못된 Token 형식 입니다"),
    AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "T003", "인증에 실패하였습니다"),
    REFRESH_TOKEN_NOT_VALID(HttpStatus.BAD_REQUEST, "T004", "refresh token 이 일치하지 않습니다"),
    MEMBER_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "", "멤버를 찾을 수 없습니다"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "", "이미 회원가입된 EMAIL 주소입니다")

}