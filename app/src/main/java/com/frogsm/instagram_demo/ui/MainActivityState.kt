package com.frogsm.instagram_demo.ui

import com.frogsm.instagram_demo.util.Event

interface MainActivityStateBindable {
    var restartActivity: Event<Unit>?
}

class MainActivityState(
    override var restartActivity: Event<Unit>? = null
) : MainActivityStateBindable {

    fun successExpireLogin() {
        restartActivity = Event(Unit)
    }

    fun failureExpireLogin() {

    }
}
