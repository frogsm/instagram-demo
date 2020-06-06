package com.frogsm.instagram_demo.ui.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.extensions.hideKeyboard
import com.frogsm.instagram_demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
    }

    private fun initUi() {

    }

    private fun initBinding() {
        redirectUriEditBox.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                loginButton.performClick()
                true
            } else {
                false
            }
        }

        loginButton.setOnClickListener {

        }
    }

}