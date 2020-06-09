package com.frogsm.instagram_demo.ui.mediacollection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionAdapter
import kotlinx.android.synthetic.main.fragment_media_collection.*
import javax.inject.Inject

class MediaCollectionFragment : BaseFragment(R.layout.fragment_media_collection) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MediaCollectionViewModel> { viewModelFactory }

    @Inject
    lateinit var mediaCollectionAdapter: MediaCollectionAdapter

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

        collectionList.apply {
            adapter = mediaCollectionAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            itemAnimator = null
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) {

            mediaCollectionAdapter.replaceData(it.mediaCollectionItems)
        }
    }
}