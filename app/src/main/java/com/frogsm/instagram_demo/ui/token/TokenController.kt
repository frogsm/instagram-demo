package com.frogsm.instagram_demo.ui.token

interface TokenController {
    fun start(
        clientId: String,
        clientSecretId: String,
        redirectUri: String
    )

    fun onAuthorizeCodeObtained(authorizeCode: String)
}