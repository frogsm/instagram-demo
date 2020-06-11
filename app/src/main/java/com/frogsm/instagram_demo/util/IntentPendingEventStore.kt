package com.frogsm.instagram_demo.util

import android.content.Intent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface IntentPendingEventStore {
    // 토큰 만료 메세지를 새 액티비티가 열린 후 보여주기 위함
    var tokenExpiredMessage: String?
}

class IntentPendingEventStoreImpl(intent: Intent) : IntentPendingEventStore {

    override var tokenExpiredMessage
            by StringIntentExtra(intent, "tokenExpiredMessage", null)
}

private class StringIntentExtra(
    private val intent: Intent,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return intent.getStringExtra(name)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        intent.putExtra(name, value)
    }
}
