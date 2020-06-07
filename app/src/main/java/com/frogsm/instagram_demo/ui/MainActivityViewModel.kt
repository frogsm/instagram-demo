package com.frogsm.instagram_demo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.login.ExpireLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val expireLogin: ExpireLogin
) : ViewModel(), GlobalListener {

    val liveData = MutableLiveData<MainActivityStateBindable>()
    private val state = MainActivityState()

    override fun onAllErrorsForToken() {
        viewModelScope.launch {
            launch { expireLogin() }
        }
    }

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