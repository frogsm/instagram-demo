package com.frogsm.instagram_demo.ui.splash

import com.frogsm.instagram_demo.util.Event

interface SplashStateBindable {
    var navigateLogin: Event<Unit>?
    var navigateMediaCollection: Event<String>?
}

class SplashState(
    override var navigateLogin: Event<Unit>? = null,
    override var navigateMediaCollection: Event<String>? = null
) : SplashStateBindable {

    fun successGetUser(userName: String) {
        navigateMediaCollection = Event(userName)
    }

    fun failureGetUser() {
        navigateLogin = Event(Unit)
    }
}