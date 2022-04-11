package com.project.meshq.core.config

import com.project.meshq.application.member.domain.Role
import com.project.meshq.core.jwt.JwtAuthenticationFilter
import com.project.meshq.core.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtTokenProvider: JwtTokenProvider
): WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //session stop

        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/member/**").hasRole("USER")
            .antMatchers("/signup", "/login",
                "/swagger-ui/**", "/swagger-resources/**", "/swagger/**", "/webjars/**", "/swagger-ui.html",
                "/v2/api-docs", "/v3/api-docs", "/h2-console/**", "/").permitAll()
            .anyRequest().authenticated()

        //UsernamePassword Filter 이전 JwtCustomFilter 를 등록하는 과정
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

}