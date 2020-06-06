package com.frogsm.instagram_demo.ui.login

import android.content.res.Resources
import com.frogsm.instagram_demo.R

interface LoginStateBindable {
    var clientId: String
    var redirectUri: String
}

class LoginState(
    resources: Resources,
    override var clientId: String = resources.getString(R.string.client_id),
    override var redirectUri: String = resources.getString(R.string.redirect_url)
) : LoginStateBindable {

    fun onClientIdChanged(text: CharSequence?) {
        clientId = text?.toString() ?: ""
    }

    fun onRedirectUriChanged(text: CharSequence?) {
        redirectUri = text?.toString() ?:""
    }

}