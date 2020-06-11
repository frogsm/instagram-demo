package com.frogsm.instagram_demo.data.media.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class MediaDataRM(
    @PrimaryKey
    var id: String = "",
    var mediaType: String = "",
    var mediaUrl: String = ""
) : RealmObject()