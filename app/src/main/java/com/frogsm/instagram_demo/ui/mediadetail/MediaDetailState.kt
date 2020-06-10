package com.frogsm.instagram_demo.ui.mediadetail

interface MediaDetailStateBindable {
    var toolbarTitle: String
}

class MediaDetailState(
    override var toolbarTitle: String = ""
) : MediaDetailStateBindable {

    fun initialize(userName: String) {
        toolbarTitle = userName
    }
}