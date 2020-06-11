package com.frogsm.instagram_demo.ui.login

import android.content.res.Resources
import android.text.Editable
import androidx.annotation.StringRes
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.util.Event

interface LoginStateBindable {
    var clientId: String
    var clientSecretId: String
    var redirectUri: String
    var showSnackBar: Event<String>?
    var navigateToken: Event<Unit>?
    var navigateMediaCollection: Event<String>?
}

class LoginState(
    private val resources: Resources,
    override var clientId: String = resources.getString(R.string.user_id),
    override var clientSecretId: String = resources.getString(R.string.user_secret_id),
    override var redirectUri: String = resources.getString(R.string.redirect_url),
    override var showSnackBar: Event<String>? = null,
    override var navigateToken: Event<Unit>? = null,
    override var navigateMediaCollection: Event<String>? = null
) : LoginStateBindable {

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
        navigateToken = Event(Unit)
    }

    fun failureValidateLogin(@StringRes resId: Int) {
        showSnackBar = Event(resources.getString(resId))
    }

}