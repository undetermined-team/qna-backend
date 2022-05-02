package com.project.meshq.core.oauth
import com.project.meshq.application.member.application.service.CrudMemberService
import com.project.meshq.application.util.*
import com.project.meshq.core.jwt.JwtTokenProvider
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Lazy

@Component
class OAuth2SuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider,
    @Lazy private val memberService: CrudMemberService
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuth2User = authentication.principal as OAuth2User
        val oAuth2Model = toOAuth2Model(oAuth2User)

//        val tokenDataModel = if (isMember(oAuth2Model.email)) {
//            memberService.getTokenDataModelByEmail(oAuth2Model.email)
//        } else {
//            val memberId = socialSignUp(oAuth2Model)
//            memberService.getMemberById(memberId)
//        }

        //Token 반환
//        val accessToken = jwtTokenProvider.createToken(responseMember, ACCESS_TOKEN_VALID_TIME)
//        response.addCookie(
//            cookieGenerator(
//                ACCESS_TOKEN,
//                accessToken
//            )
//        )

//        memberService.updateRefreshToken(tokenDataModel.id, refreshToken)
        response.sendRedirect(LOGIN_REDIRECT_URL)
    }


    private fun toOAuth2Model(oAuth2User: OAuth2User): OAuth2Model {
        return OAuth2Model(
            id = oAuth2User.attributes["id"] as String,
            email = oAuth2User.attributes["email"] as String,
        )
    }

//    private fun isMember(email: String): Boolean {
//        return memberService.checkEmail(email)
//    }

    //회원가입
//    private fun socialSignUp(oAuth2Model: OAuth2Model): Long {
//        return memberService.createMemberBySocial(oAuth2Model)
//    }
}