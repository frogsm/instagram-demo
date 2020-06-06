package com.frogsm.instagram_demo.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.domain.login.ValidateLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    context: Context,
    private val validateLogin: ValidateLogin
) : ViewModel(), LoginController {

    val liveData = MutableLiveData<LoginStateBindable>()
    private val state = LoginState(context.resources)

    override fun start() {
        liveData.postValue(state)
    }

    override fun onClientIdChanged(text: CharSequence?) {
        state.onClientIdChanged(text)
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