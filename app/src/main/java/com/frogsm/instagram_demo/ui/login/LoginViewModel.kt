package com.frogsm.instagram_demo.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    context: Context
) : BaseViewModel(), LoginController {

    val liveData = MutableLiveData<LoginStateBindable>()
    private val state = LoginState(context.resources)

    override fun start() {
        liveData.postValue(state)
    }

    override fun onLoginButtonClicked() {

    }

    override fun onClientIdChanged(text: CharSequence?) {
        state.onClientIdChanged(text)
        liveData.postValue(state)
    }

    override fun onRedirectUriChanged(text: CharSequence?) {
        state.onRedirectUriChanged(text)
        liveData.postValue(state)
    }
}