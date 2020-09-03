package com.frogsm.instagram_demo.ui.login

import android.text.Editable

interface LoginController {
    fun start()

    fun onClientIdChanged(text: Editable?)

    fun onClientSecretIdChanged(text: Editable?)

    fun onRedirectUriChanged(text: Editable?)

    fun onLoginButtonClicked()
}