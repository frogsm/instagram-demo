package com.frogsm.instagram_demo.ui.mediadetail

import androidx.lifecycle.MutableLiveData
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import javax.inject.Inject

class MediaDetailViewModel @Inject constructor(
    globalListener: GlobalListener
) : BaseViewModel(globalListener), MediaDetailController {

    val liveData = MutableLiveData<MediaDetailStateBindable>()
    private val state = MediaDetailState()

    override fun start(userName: String) {
        state.initialize(userName)
        liveData.postValue(state)
    }
}