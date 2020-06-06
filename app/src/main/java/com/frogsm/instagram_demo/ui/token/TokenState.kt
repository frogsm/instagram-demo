package com.frogsm.instagram_demo.ui.token

import com.frogsm.instagram_demo.domain.entity.AuthorizeUri
import com.frogsm.instagram_demo.util.Event

interface TokenStateBindable {
    var displayUrl: Event<String>?
}

class TokenState(
    override var displayUrl: Event<String>? = null
) : TokenStateBindable {

    var clientId: String = ""
    var clientSecretId: String = ""
    var redirectUri: String = ""

    fun initialize(
        clientId: String,
        clientSecretId: String,
        redirectUri: String
    ) {
        this.clientId = clientId
        this.clientSecretId = clientSecretId
        this.redirectUri = redirectUri
    }

    fun successCreateOauthAuthorizeUri(authorizeUri: AuthorizeUri) {
        displayUrl = Event(authorizeUri.uri)
    }

    fun failureCreateOauthAuthorizeUri() {

    }
}