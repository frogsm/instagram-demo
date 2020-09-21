package com.frogsm.instagram_demo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.domain.usecase.login.ExpireLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext

class MainActivityViewModel @ViewModelInject constructor(
    private val expireLogin: ExpireLogin
) : ViewModel() {

    val liveData = MutableLiveData<MainActivityStateBindable>()
    private val state = MainActivityState()

    private suspend fun expireLogin(
    ) = withContext(Dispatchers.IO) {

        expireLogin(Unit)
            .onSuccess {
                state.successExpireLogin()
                liveData.postValue(state)
            }
            .onFailure {
                cancel()
                state.failureExpireLogin()
                liveData.postValue(state)
            }
    }
}