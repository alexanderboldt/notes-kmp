package org.alex.storage

import com.russhwolf.settings.Settings

class UserSettings {

    private val KEY_ACCESS_TOKEN = "access_token"

    private val settings: Settings = Settings()

    fun putAccessToken(accessToken: String) {
        settings.putString(KEY_ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken() = settings.getString(KEY_ACCESS_TOKEN, "")
}
