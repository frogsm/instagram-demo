package com.frogsm.instagram_demo.ui.mediacollection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_media_collection.*
import javax.inject.Inject

class MediaCollectionFragment : BaseFragment(R.layout.fragment_media_collection) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MediaCollectionViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start()
    }

    private fun initUi() {
        testButton.setOnClickListener {
            viewModel.start()
        }
    }

    private fun initBinding() {

    }
}