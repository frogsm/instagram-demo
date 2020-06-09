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

    val liveData = MutableLiveData<LoginStateBindable>()
    private val state = LoginState(context.resources)

    override fun start() {
        viewModelScope.launch {
            launch { getUser() }
        }
    }

    override fun onClientIdChanged(text: CharSequence?) {
        state.onClientIdChanged(text)
        liveData.postValue(state)
    }

    override fun onClientSecretIdChanged(text: Editable?) {
        state.onClientSecretIdChanged(text)
        liveData.postValue(state)
    }

    override fun onRedirectUriChanged(text: CharSequence?) {
        state.onRedirectUriChanged(text)
        liveData.postValue(state)
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
                liveData.postValue(state)
            }
            .onFailure {
                cancel()
                state.failureGetUser()
                liveData.postValue(state)
            }
    }

    private suspend fun validateLogin(
    ) = withContext(Dispatchers.IO) {

        ValidateLogin.Request(state.clientId, state.redirectUri)
            .run { validateLogin(this) }
            .onSuccess {
                state.successValidateLogin()
                liveData.postValue(state)
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
                liveData.postValue(state)
            }
    }
}