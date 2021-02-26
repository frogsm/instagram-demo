package com.frogsm.instagram_demo.extensions

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.requestFocusLastCharacterIfNotEmpty() {
    setOnFocusChangeListener { v, _ ->
        val view = v as EditText
        val text = view.text

        if (text.isNotEmpty()) {
            view.setSelection(text.length)
        }
    }
}