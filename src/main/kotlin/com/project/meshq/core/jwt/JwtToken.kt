package com.project.meshq.core.jwt

import com.project.meshq.application.util.TOKEN_VALID_TIME
import io.jsonwebtoken.*
import java.util.*

class JwtToken(
    private val token: String = "",
    private var key: String,
    private val id: String = "",
    private val role: String = ""
) {
    init {
        key = Base64.getEncoder().encodeToString(key.toByteArray())
    }

    fun validate(): Boolean {
        try {
            return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .body.expiration.after(Date())
        } catch (e: SecurityException) {
            println("Invalid JWT signature.");
        } catch (e: MalformedJwtException) {
            println("Invalid JWT token.");
        } catch (e: ExpiredJwtException) {
            println("Expired JWT token.");
        } catch (e: UnsupportedJwtException) {
            println("Unsupported JWT token.");
        } catch (e: IllegalArgumentException) {
            println("JWT token compact of handler are invalid.");
        }
        return false
    }

    fun createToken(): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setSubject(id)
            .claim("role", role)
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_VALID_TIME))
            .signWith(SignatureAlgorithm.HS256, key)
            .compact()

    }
    fun getClaims(): String {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .body
            .subject
    }
}