package com.frogsm.instagram_demo.ui.login

import android.content.res.Resources
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.util.Event

interface LoginStateBindable {
    var clientId: String
    var redirectUri: String
    var navigateToken: Event<Unit>?
}

class LoginState(
    resources: Resources,
    override var clientId: String = resources.getString(R.string.client_id),
    override var redirectUri: String = resources.getString(R.string.redirect_url),
    override var navigateToken: Event<Unit>? = null
) : LoginStateBindable {

    fun onClientIdChanged(text: CharSequence?) {
        clientId = text?.toString() ?: ""
    }

    fun onRedirectUriChanged(text: CharSequence?) {
        redirectUri = text?.toString() ?: ""
    }

    fun onLoginButtonClicked() {
        navigateToken = Event(Unit)
    }

}