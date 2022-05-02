package com.project.meshq.application.util

const val ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60//1시간
const val REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 30//30일
const val ROLE_ADMIN = "ADMIN"
const val ROLE_USER = "MEMBER"

const val LOGIN_REDIRECT_URL = "http://localhost:8081/oauth/redirect"
const val ACCESS_TOKEN = "access_token"
const val REFRESH_TOKEN = "refresh_token"