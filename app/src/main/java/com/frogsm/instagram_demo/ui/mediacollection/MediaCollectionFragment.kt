package com.frogsm.instagram_demo.ui.mediacollection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
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
    private val args by navArgs<MediaCollectionFragmentArgs>()

    @Inject
    lateinit var mediaCollectionAdapter: MediaCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start(args.userName)
    }

    private fun initUi() {
        collectionList.apply {
            adapter = mediaCollectionAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            itemAnimator = null
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            toolbar.title = state.toolbarTitle

            mediaCollectionAdapter.replaceData(state.mediaCollectionItems)
        }
    }
}