package com.frogsm.instagram_demo.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferencesImpl @Inject constructor(
    sharedPreferences: SharedPreferences
) : Preferences {

    override var accessToken: String
        by NonNullStringPreference(sharedPreferences, "accessToken", "")
}

private open class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit { putString(name, value) }
    }
}

private class NonNullStringPreference(
    preferences: SharedPreferences,
    name: String,
    defaultValue: String
) : StringPreference(preferences, name, defaultValue) {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return super.getValue(thisRef, property)!!
    }
}