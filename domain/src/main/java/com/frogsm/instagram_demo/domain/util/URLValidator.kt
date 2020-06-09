package com.frogsm.instagram_demo.domain.util

import java.net.MalformedURLException
import java.net.URL

object URLValidator {

    fun isValid(url: String): Boolean = try {
        URL(url)
        true
    } catch (throwable: MalformedURLException) {
        false
    }
}