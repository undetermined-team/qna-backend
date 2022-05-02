package com.project.meshq.core.config

import com.project.meshq.application.member.domain.Role
import com.project.meshq.core.exception.CustomAccessDeniedHandler
import com.project.meshq.core.exception.CustomAuthenticationEntryPoint
import com.project.meshq.core.jwt.JwtAuthenticationFilter
import com.project.meshq.core.jwt.JwtTokenProvider
import com.project.meshq.core.oauth.CustomOAuth2UserService
import com.project.meshq.core.oauth.OAuth2SuccessHandler
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
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val oAuth2SuccessHandler: OAuth2SuccessHandler
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/member/**").hasRole("USER")
            .antMatchers("/signup", "/login",
                "/swagger-ui/**", "/swagger-resources/**", "/swagger/**", "/webjars/**", "/swagger-ui.html",
                "/v2/api-docs", "/v3/api-docs", "/h2-console/**", "/").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)
            .accessDeniedHandler(customAccessDeniedHandler)
        .and()
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java)


        http
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
        .and()
            .successHandler(oAuth2SuccessHandler)
    }

}