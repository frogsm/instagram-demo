package com.frogsm.instagram_demo.ui.splash

import androidx.lifecycle.MutableLiveData
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
) : BaseViewModel() {

    val liveData = MutableLiveData<SplashStateBindable>()
    private val state = SplashState()

    override fun start() {
        state.navigateLogin()
        liveData.postValue(state)
    }
}