package com.frogsm.instagram_demo.ui.token

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import javax.inject.Inject

class TokenFragment : BaseFragment(R.layout.fragment_token) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<TokenViewModel> { viewModelFactory }
    private val args by navArgs<TokenFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start(args.clientId, args.redirectUri)
    }

    private fun initUi() {

    }

    private fun initBinding() {

    }
}