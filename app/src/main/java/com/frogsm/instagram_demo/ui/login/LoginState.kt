package com.frogsm.instagram_demo.ui.login

import android.content.res.Resources
import androidx.annotation.StringRes
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.util.Event

interface LoginStateBindable {
    var clientId: String
    var redirectUri: String
    var showSnackBar: Event<String>?
    var navigateToken: Event<Unit>?
}

class LoginState(
    private val resources: Resources,
    override var clientId: String = resources.getString(R.string.client_id),
    override var redirectUri: String = resources.getString(R.string.redirect_url),
    override var showSnackBar: Event<String>? = null,
    override var navigateToken: Event<Unit>? = null
) : LoginStateBindable {

    fun onClientIdChanged(text: CharSequence?) {
        clientId = text?.toString() ?: ""
    }

    fun onRedirectUriChanged(text: CharSequence?) {
        redirectUri = text?.toString() ?: ""
    }

    fun successValidateLogin() {
        navigateToken = Event(Unit)
    }

    fun failureValidateLogin(@StringRes resId: Int) {
        showSnackBar = Event(resources.getString(resId))
    }

}