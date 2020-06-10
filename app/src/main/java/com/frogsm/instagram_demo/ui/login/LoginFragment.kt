package com.frogsm.instagram_demo.ui.login

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.extensions.hideKeyboard
import com.frogsm.instagram_demo.extensions.navigateSafely
import com.frogsm.instagram_demo.extensions.showLongSnackBar
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start()
    }

    private fun initUi() {
        clientIdEditBox.doAfterTextChanged { text ->
            viewModel.onClientIdChanged(text)
        }

        clientSecretIdEditBox.doAfterTextChanged { text ->
            viewModel.onClientSecretIdChanged(text)
        }

        redirectUriEditBox.doAfterTextChanged { text ->
            viewModel.onRedirectUriChanged(text)
        }

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
            viewModel.onLoginButtonClicked()
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            clientIdEditBox
                .takeIf { it.text.toString() != state.clientId }
                ?.run { text = SpannableStringBuilder(state.clientId) }

            clientSecretIdEditBox
                .takeIf { it.text.toString() != state.clientId }
                ?.run { text = SpannableStringBuilder(state.clientSecretId) }

            redirectUriEditBox
                .takeIf { it.text.toString() != state.redirectUri }
                ?.run { text = SpannableStringBuilder(state.redirectUri) }

            state.showSnackBar?.observeOnlyOnce {
                showLongSnackBar(it)
            }

            state.navigateToken?.observeOnlyOnce {
                val action = LoginFragmentDirections.actionLoginFragmentToAuthorizeFragment(
                    clientId = state.clientId,
                    clientSecretId = state.clientSecretId,
                    redirectUri = state.redirectUri
                )
                findNavController().navigateSafely(action)
            }

            state.navigateMediaCollection?.observeOnlyOnce { userName ->
                val action = LoginFragmentDirections.actionLoginFragmentToMediaCollectionFragment(
                    userName = userName
                )
                findNavController().run {
                    graph.startDestination = R.id.mediaCollectionFragment
                    navigateSafely(action)
                }
            }
        }
    }

}