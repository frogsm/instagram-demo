package com.frogsm.instagram_demo.ui.authorize

interface AuthorizeController {
    fun start(
        clientId: String,
        clientSecretId: String,
        redirectUri: String
    )

    fun onAuthorizeCodeObtained(authorizeCode: String)
}