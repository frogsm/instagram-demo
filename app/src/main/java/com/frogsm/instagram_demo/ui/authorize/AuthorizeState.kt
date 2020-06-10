package com.frogsm.instagram_demo.ui.authorize

import android.content.res.Resources
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.domain.entity.AuthorizeUri
import com.frogsm.instagram_demo.util.Event

interface TokenStateBindable {
    var displayUrl: Event<String>?
    var showSnackBar: Event<String>?
    var navigateBack: Event<Unit>?
}

class AuthorizeState(
    private val resources: Resources,
    override var displayUrl: Event<String>? = null,
    override var showSnackBar: Event<String>? = null,
    override var navigateBack: Event<Unit>? = null
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
        showSnackBar = Event(resources.getString(R.string.error_oauth_authorize_uri))
    }

    fun successCreateAccessToken() {
        navigateBack = Event(Unit)
    }

    fun failureCreateAccessToken() {
        showSnackBar = Event(resources.getString(R.string.error_access_token_obtain))
    }
}