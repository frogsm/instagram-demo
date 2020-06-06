package com.frogsm.instagram_demo.ui.token

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TokenViewModel @Inject constructor(

) : ViewModel(), TokenController {

    override fun start(clientId: String, redirectUri: String) {

    }
}