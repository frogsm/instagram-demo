package com.frogsm.instagram_demo.ui.splash

import com.frogsm.instagram_demo.util.Event

interface SplashStateBindable {
    var navigateLogin: Event<Unit>?
}

class SplashState(
    override var navigateLogin: Event<Unit>? = null
) : SplashStateBindable {

    fun navigateLogin() {
        navigateLogin = Event(Unit)
    }
}