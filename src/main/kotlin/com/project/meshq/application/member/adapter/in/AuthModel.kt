package com.daou.hc.member.controller

import com.daou.hc.member.domain.entity.AgeType
import com.daou.hc.member.domain.entity.GenderType
import com.daou.hc.member.domain.entity.RoleType
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignInModel(
    @field:Email(message = "잘못된 Email 형식입니다")
    val email: String,
    @field:NotBlank(message = "패스워드를 입력해 주세요")
    val password: String,
    val otp: String?
)

data class SignUpModel(
    @field:Email(message = "잘못된 Email 형식입니다")
    val email: String,
    val profileName: String,
    @field:NotBlank(message = "패스워드를 입력해 주세요")
    val password: String,
    val gender: GenderType,
    val age: AgeType,
)

data class TokenDataModel(
    val id: Long?,
    val email: String,
    val role: RoleType
)
