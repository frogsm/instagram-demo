package com.frogsm.instagram_demo.ui.token

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.token.CreateOauthAuthorizeUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenViewModel @Inject constructor(
    private val createOauthAuthorizeUri: CreateOauthAuthorizeUri
) : ViewModel(), TokenController {

    val liveData = MutableLiveData<TokenStateBindable>()
    private val state = TokenState()

    override fun start(
        clientId: String,
        redirectUri: String
    ) {
        viewModelScope.launch {
            launch { createOauthAuthorizeUri(clientId, redirectUri) }
        }
    }

    override fun onAuthorizeCodeObtained(authorizeCode: String) {

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
}