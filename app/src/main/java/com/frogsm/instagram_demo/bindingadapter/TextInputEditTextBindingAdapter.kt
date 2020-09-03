package com.frogsm.instagram_demo.bindingadapter

import android.text.Editable
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("android:doAfterTextChanged")
internal fun doAfterTextChanged(
    view: TextInputEditText,
    action: TextInputEditTextBindingAdapter.DoAfterTextChanged
) {
    view.addTextChangedListener(afterTextChanged = action::doAfterTextChanged)
}

object TextInputEditTextBindingAdapter {
    internal interface DoAfterTextChanged {
        fun doAfterTextChanged(text: Editable?)
    }
}
