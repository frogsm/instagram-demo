package com.frogsm.instagram_demo.data.realm

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class MyRealm @Inject constructor(
    @ApplicationContext context: Context
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