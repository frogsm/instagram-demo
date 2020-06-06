package com.frogsm.instagram_demo.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val view = view ?: return
    val inputManager =
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager) ?: return

    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}