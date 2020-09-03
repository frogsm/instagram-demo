package com.frogsm.instagram_demo.ui.login

import android.content.res.Resources
import android.text.Editable
import androidx.annotation.StringRes
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.util.Event

interface LoginStateSingleEvent {
    var showSnackBar: Event<String>?
    var navigateToken: Event<NavigateTokenInfo>?
    var navigateMediaCollection: Event<String>?
}

class LoginState(
    private val resources: Resources,
    override var showSnackBar: Event<String>? = null,
    override var navigateToken: Event<NavigateTokenInfo>? = null,
    override var navigateMediaCollection: Event<String>? = null
) : LoginStateSingleEvent {

    var clientId = resources.getString(R.string.user_id)
        private set

    var clientSecretId = resources.getString(R.string.user_secret_id)
        private set

    var redirectUri = resources.getString(R.string.redirect_url)
        private set

    fun onClientIdChanged(text: CharSequence?) {
        clientId = text?.toString() ?: ""
    }

    fun onClientSecretIdChanged(text: Editable?) {
        clientSecretId = text?.toString() ?: ""
    }

    fun onRedirectUriChanged(text: CharSequence?) {
        redirectUri = text?.toString() ?: ""
    }

    fun successGetUser(userName: String) {
        navigateMediaCollection = Event(userName)
    }

    fun failureGetUser() {

    }

    fun successValidateLogin() {
        val info = NavigateTokenInfo(clientId, clientSecretId, redirectUri)
        navigateToken = Event(info)
    }

    fun failureValidateLogin(@StringRes resId: Int) {
        showSnackBar = Event(resources.getString(resId))
    }

}

data class NavigateTokenInfo(
    val clientId: String,
    val clientSecretId: String,
    val redirectUri: String
)