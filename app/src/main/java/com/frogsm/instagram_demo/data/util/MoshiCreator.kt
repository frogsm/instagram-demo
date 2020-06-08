package com.frogsm.instagram_demo.data.util

import com.frogsm.instagram_demo.data.media.MediaTypeData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

class MoshiCreator @Inject constructor() {

    fun createMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(MediaTypeData.MoshiAdapter())
        .build()
}