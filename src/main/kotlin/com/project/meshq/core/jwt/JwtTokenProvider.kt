package com.project.meshq.core.jwt
import com.project.meshq.application.member.adapter.`in`.ResponseMember
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(
    @Value("\${JWT.TOKEN.KEY}")
    private var key: String
) {

    @PostConstruct
    fun init() {
        key = Base64.getEncoder().encodeToString(key.toByteArray())
    }

    fun createToken(memberModel: ResponseMember, validTime: Long): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setSubject(memberModel.snsId)
            .claim("role", memberModel.role)
            .claim("email", memberModel.email)
            .setExpiration(Date(System.currentTimeMillis() + validTime))
            .signWith(SignatureAlgorithm.HS256, key)
            .compact()
    }

    fun resolve(request: HttpServletRequest): String {
        return request.getHeader("x-auth-token") ?: ""
    }

    fun validate(token: String, request: HttpServletRequest): Boolean {
        try {
            return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .body
                .expiration
                .after(Date())
        } catch (e: ExpiredJwtException) {
            request.setAttribute("exception", "TIME_EXPIRED_TOKEN")
        } catch (e: JwtException) {
            request.setAttribute("exception", "WRONG_TOKEN")
        }
        return false
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)

        val authorities: Collection<GrantedAuthority?> =
            Arrays.stream(arrayOf(claims.body["role"].toString()))
                .map(::SimpleGrantedAuthority)
                .collect(Collectors.toList())

        val userDetails = User(claims.body["sub"].toString(), "", authorities)
        return UsernamePasswordAuthenticationToken(
            userDetails,
            "",
            userDetails.authorities
        )
    }
}