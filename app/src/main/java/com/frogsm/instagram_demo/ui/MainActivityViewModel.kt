package com.frogsm.instagram_demo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.domain.usecase.login.ExpireLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivityViewModel @ViewModelInject constructor(
    private val expireLogin: ExpireLogin
) : ViewModel() {

    val liveData = MutableLiveData<MainActivityStateBindable>()
    private val state = MainActivityState()

    fun onCommonErrorHandle(throwable: Throwable) {
        viewModelScope.launch {

            val httpException = throwable as? HttpException
            httpException?.run {
                when (code()) {
                    /* 토큰이 만료되거나 토큰정보가 잘못 되었을때, 화면 재시작 */
                    400 -> expireLogin()
                    else -> Unit
                }
            }
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