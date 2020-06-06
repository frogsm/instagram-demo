package com.frogsm.instagram_demo.ui.token

interface TokenController {
    fun start(clientId: String, redirectUri: String)
}