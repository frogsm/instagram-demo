package com.frogsm.instagram_demo.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.token.ValidateAccessToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val validateAccessToken: ValidateAccessToken
) : ViewModel(), SplashController {

    val liveData = MutableLiveData<SplashStateBindable>()
    private val state = SplashState()

    override fun start() {
        viewModelScope.launch {
            launch { validateAccessToken() }
        }
    }

    private suspend fun validateAccessToken(
    ) = withContext(Dispatchers.IO) {

        validateAccessToken(Unit)
            .onSuccess {
                state.successValidateAccessToken()
                liveData.postValue(state)
            }
            .onFailure {
                cancel()
                state.failureValidateAccessToken()
                liveData.postValue(state)
            }
    }
}