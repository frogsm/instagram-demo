package com.frogsm.instagram_demo.ui.login

interface LoginController {
    fun start()

    fun onClientIdChanged(text: CharSequence?)

    fun onRedirectUriChanged(text: CharSequence?)

    fun onLoginButtonClicked()
}