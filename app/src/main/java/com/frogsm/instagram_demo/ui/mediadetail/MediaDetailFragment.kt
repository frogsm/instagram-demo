package com.frogsm.instagram_demo.ui.mediadetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_media_detail.*
import javax.inject.Inject

class MediaDetailFragment : BaseFragment(R.layout.fragment_media_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MediaDetailViewModel> { viewModelFactory }
    private val args by navArgs<MediaDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start(args.userName)
    }

    private fun initUi() {

    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->

            toolbar.title = state.toolbarTitle
        }
    }
}