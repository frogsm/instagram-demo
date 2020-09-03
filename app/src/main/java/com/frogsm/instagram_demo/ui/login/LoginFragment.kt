package com.frogsm.instagram_demo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.databinding.FragmentLoginBinding
import com.frogsm.instagram_demo.extensions.hideKeyboard
import com.frogsm.instagram_demo.extensions.navigateSafely
import com.frogsm.instagram_demo.extensions.showLongSnackBar
import com.frogsm.instagram_demo.ui.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var binding : FragmentLoginBinding? = null
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start()
    }

    private fun initUi() {
        binding?.run {

            redirectUriEditBox.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard()
                    loginButton.performClick()
                    true
                } else {
                    false
                }
            }

        }
    }

    private fun initBinding() {
        viewModel.singleLiveEvent.observe(viewLifecycleOwner) { event ->

            event.showSnackBar?.observeOnlyOnce {
                showLongSnackBar(it)
            }

            event.navigateToken?.observeOnlyOnce { info ->
                val action = LoginFragmentDirections.actionLoginFragmentToAuthorizeFragment(
                    clientId = info.clientId,
                    clientSecretId = info.clientSecretId,
                    redirectUri = info.redirectUri
                )
                findNavController().navigateSafely(action)
            }

            event.navigateMediaCollection?.observeOnlyOnce { userName ->
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