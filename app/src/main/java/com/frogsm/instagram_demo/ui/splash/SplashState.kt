package com.frogsm.instagram_demo.ui.splash

import com.frogsm.instagram_demo.util.Event

interface SplashStateBindable {
    var navigateLogin: Event<Unit>?
    var navigateMediaCollection: Event<Unit>?
}

class SplashState(
    override var navigateLogin: Event<Unit>? = null,
    override var navigateMediaCollection: Event<Unit>? = null
) : SplashStateBindable {

    fun successValidateAccessToken() {
        navigateMediaCollection = Event(Unit)
    }

    fun failureValidateAccessToken() {
        navigateLogin = Event(Unit)
    }
}