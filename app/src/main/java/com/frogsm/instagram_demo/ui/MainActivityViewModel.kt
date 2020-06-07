package com.frogsm.instagram_demo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
) : ViewModel(), GlobalListener {

    val liveData = MutableLiveData<MainActivityStateBindable>()
    private val state = MainActivityState()

    override fun restartActivity() {
        state.restartActivity()
        liveData.postValue(state)
    }
}