package com.frogsm.instagram_demo.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.extensions.navigateSafely
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start()
    }

    private fun initUi() {

    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->

            state.navigateLogin?.observeOnlyOnce {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                findNavController().run {
                    graph.startDestination = R.id.loginFragment
                    navigateSafely(action)
                }
            }

            state.navigateMediaCollection?.observeOnlyOnce { userName ->
                val action = SplashFragmentDirections.actionSplashFragmentToMediaCollectionFragment(
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