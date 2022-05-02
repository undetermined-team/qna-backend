package com.project.meshq.application.util

import javax.servlet.http.Cookie

fun cookieGenerator(
    key: String,
    value: String,
): Cookie {
    val cookie = Cookie(key, value)
    cookie.path = "/"

    return cookie
}