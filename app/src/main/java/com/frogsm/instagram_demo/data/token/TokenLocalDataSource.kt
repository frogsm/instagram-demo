package com.frogsm.instagram_demo.data.token

import com.frogsm.instagram_demo.data.preferences.Preferences
import com.frogsm.instagram_demo.domain.entity.AccessToken
import javax.inject.Inject

class TokenLocalDataSource @Inject constructor(
    private val preferences: Preferences
) {
    private var memoryAccessToken: AccessToken? = null

    private var diskAccessToken: AccessToken?
        set(value) {
            preferences.accessToken = value?.token ?: ""
        }
        get() = AccessToken(preferences.accessToken)
            .takeIf { it.token.isNotEmpty() }

    fun getAccessToken(): AccessToken? {
        return memoryAccessToken
            ?: diskAccessToken?.also { memoryAccessToken = it }
    }

    fun updateAccessToken(tokenData: AccessTokenData) {
        with(AccessToken(tokenData.access_token)) {
            diskAccessToken = this
            memoryAccessToken = this
        }
    }

    fun clearAccessToken() {
        diskAccessToken = null
        memoryAccessToken = null
    }
}