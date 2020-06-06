package com.frogsm.instagram_demo.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import javax.inject.Inject

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<SplashViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}