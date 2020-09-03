package com.frogsm.instagram_demo.ui.login

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.domain.usecase.login.ValidateLogin
import com.frogsm.instagram_demo.domain.usecase.user.GetUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    context: Context,
    private val getUser: GetUser,
    private val validateLogin: ValidateLogin
) : ViewModel(), LoginController {

    private val state = LoginState(context.resources)
    val singleLiveEvent = MutableLiveData<LoginStateSingleEvent>()

    val clientId = MutableLiveData(state.clientId)
    val clientSecretId = MutableLiveData(state.clientSecretId)
    val redirectUri = MutableLiveData(state.redirectUri)

    override fun start() {
        viewModelScope.launch {
            launch { getUser() }
        }
    }

    override fun onClientIdChanged(text: Editable?) {
        state.onClientIdChanged(text)
        clientId.postValue(state.clientId)
    }

    override fun onClientSecretIdChanged(text: Editable?) {
        state.onClientSecretIdChanged(text)
        clientSecretId.postValue(state.clientSecretId)
    }

    override fun onRedirectUriChanged(text: Editable?) {
        state.onRedirectUriChanged(text)
        redirectUri.postValue(state.redirectUri)
    }

    override fun onLoginButtonClicked() {
        viewModelScope.launch {
            launch { validateLogin() }
        }
    }

    private suspend fun getUser(
    ) = withContext(Dispatchers.IO) {

        getUser(Unit)
            .onSuccess {
                state.successGetUser(it.name)
                singleLiveEvent.postValue(state)
            }
            .onFailure {
                cancel()
                state.failureGetUser()
                singleLiveEvent.postValue(state)
            }
    }

    private suspend fun validateLogin(
    ) = withContext(Dispatchers.IO) {

        ValidateLogin.Request(state.clientId, state.redirectUri)
            .run { validateLogin(this) }
            .onSuccess {
                state.successValidateLogin()
                singleLiveEvent.postValue(state)
            }
            .onFailure {
                cancel()
                val messageId = when (it) {
                    ValidateLogin.Exception.InvalidateRedirectUri ->
                        R.string.error_invalidate_uri
                    ValidateLogin.Exception.NotFoundedRedirectUriEndSlash ->
                        R.string.error_not_founded_slash
                    else ->
                        R.string.common_error
                }
                state.failureValidateLogin(messageId)
                singleLiveEvent.postValue(state)
            }
    }
}