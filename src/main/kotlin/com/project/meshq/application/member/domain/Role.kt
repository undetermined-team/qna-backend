package com.project.meshq.application.member.domain

enum class Role(
    val code: String,
    val description: String
) {
    ADMIN("ROLE_ADMIN", "관리자 권한"),
    USER("ROLE_USER", "사용자 권한"),
    UNKNOWN("ROLE_UNKNOWN", "알수없는 권한"),

}