package com.frogsm.instagram_demo.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
) : ViewModel(), SplashController {

    val liveData = MutableLiveData<SplashStateBindable>()
    private val state = SplashState()

    override fun start() {
        state.navigateLogin()
        liveData.postValue(state)
    }
}