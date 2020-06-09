package com.frogsm.instagram_demo.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.user.GetUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val getUser: GetUser
) : ViewModel(), SplashController {

    val liveData = MutableLiveData<SplashStateBindable>()
    private val state = SplashState()

    override fun start() {
        viewModelScope.launch {
            launch { getUser() }
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
}