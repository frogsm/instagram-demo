package com.frogsm.instagram_demo.data.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class MyRealm @Inject constructor(
    context: Context
) {
    init {
        Realm.init(context)
    }

    val realm: Realm
        get() = RealmConfiguration.Builder()
            .name("my.realm")
            .build()
            .run { Realm.getInstance(this) }
}