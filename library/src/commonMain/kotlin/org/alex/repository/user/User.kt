package org.alex.repository.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("access_token")
    val accessToken: String
)
