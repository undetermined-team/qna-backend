package com.project.meshq.application.util

fun <T> throwCheck(target: T, type: ExceptionType) {
    target ?: throw IllegalStateException("TEST")
}

const val TOKEN_VALID_TIME = 1000L * 60 * 10