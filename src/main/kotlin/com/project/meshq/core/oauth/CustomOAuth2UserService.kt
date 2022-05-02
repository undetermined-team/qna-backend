import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class CustomOAuth2UserService: OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = DefaultOAuth2UserService().loadUser(userRequest)

        val provider = getProvider(userRequest)
        val oAuth2Attribute = OAuth2Attribute.of(provider, oAuth2User.attributes)

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority("ROLE_MEMBER")),
            oAuth2Attribute.toMap(),
            "email"
        )
    }

    private fun getProvider(userRequest: OAuth2UserRequest): String {
        return userRequest.clientRegistration.registrationId
    }
}
