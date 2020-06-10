package com.frogsm.instagram_demo.ui.token

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.usecase.authorize.CreateAccessToken
import com.frogsm.instagram_demo.domain.usecase.authorize.CreateOauthAuthorizeUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenViewModel @Inject constructor(
    context: Context,
    private val createOauthAuthorizeUri: CreateOauthAuthorizeUri,
    private val createAccessToken: CreateAccessToken
) : ViewModel(), TokenController {

    val liveData = MutableLiveData<TokenStateBindable>()
    private val state = TokenState(context.resources)

    override fun start(
        clientId: String,
        clientSecretId: String,
        redirectUri: String
    ) {
        state.initialize(clientId, clientSecretId, redirectUri)

        viewModelScope.launch {
            launch { createOauthAuthorizeUri(clientId, redirectUri) }
        }
    }

    override fun onAuthorizeCodeObtained(authorizeCode: String) {
        viewModelScope.launch {
            launch {
                createAccessToken(
                    state.clientId,
                    state.clientSecretId,
                    state.redirectUri,
                    authorizeCode
                )
            }
        }
    }

    private suspend fun createOauthAuthorizeUri(
        clientId: String,
        redirectUri: String
    ) = withContext(Dispatchers.IO) {

        CreateOauthAuthorizeUri.Request(clientId, redirectUri)
            .run { createOauthAuthorizeUri(this) }
            .onSuccess {
                state.successCreateOauthAuthorizeUri(it)
                liveData.postValue(state)
            }
            .onFailure {
                cancel()
                state.failureCreateOauthAuthorizeUri()
                liveData.postValue(state)
            }
    }

    private suspend fun createAccessToken(
        clientId: String,
        clientSecretId: String,
        redirectUri: String,
        authorizeCode: String
    ) = withContext(Dispatchers.IO) {

        CreateAccessToken.Request(clientId, clientSecretId, redirectUri, authorizeCode)
            .run { createAccessToken(this) }
            .onSuccess {
                state.successCreateAccessToken()
                liveData.postValue(state)
            }
            .onFailure {
                state.failureCreateAccessToken()
                liveData.postValue(state)
            }
    }
}