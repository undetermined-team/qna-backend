package com.project.meshq.core.oauth

class OAuth2Attribute(
    private val id: String,
    private val email: String,
) {
    fun toMap(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["id"] = id
        map["email"] = email

        return map
    }

    companion object {
        fun of(provider: String, attributes: MutableMap<String, Any>): OAuth2Attribute {
            return when (provider) {
                "naver" -> naver(attributes)
                "kakao" -> kakao(attributes)
                else -> throw RuntimeException()
            }
        }

        private fun naver(attributes: MutableMap<String, Any>): OAuth2Attribute {
            val data = attributes["response"] as Map<String, Any>
            return OAuth2Attribute(
                id = data["id"].toString(),
                email = data["email"].toString(),
            )
        }

        private fun kakao(attributes: MutableMap<String, Any>): OAuth2Attribute {
            val data = attributes["kakao_account"] as Map<String, Any>
            return OAuth2Attribute(
                id = attributes["id"].toString(),
                email = data["email"].toString(),
            )
        }
    }
}
