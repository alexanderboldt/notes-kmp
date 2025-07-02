package org.alex.settings

import com.russhwolf.settings.Settings

internal class UserSettings {

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
    }

    private val settings: Settings = Settings()

    fun putAccessToken(accessToken: String) {
        settings.putString(KEY_ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken() = settings.getString(KEY_ACCESS_TOKEN, "")

    fun deleteAccessToken() = settings.remove(KEY_ACCESS_TOKEN)
}
