package com.frogsm.instagram_demo.ui.mediadetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import com.frogsm.instagram_demo.ui.mediadetail.list.MediaDetailAdapter
import kotlinx.android.synthetic.main.fragment_media_detail.*
import javax.inject.Inject

class MediaDetailFragment : BaseFragment(R.layout.fragment_media_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MediaDetailViewModel> { viewModelFactory }
    private val args by navArgs<MediaDetailFragmentArgs>()

    @Inject
    lateinit var mediaDetailAdapter: MediaDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start(args.userName, args.mediaId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        childrenList.adapter = null
    }

    private fun initUi() {
        childrenList.apply {
            adapter = mediaDetailAdapter.also {
                it.registerAdapterDataObserver(indicator.adapterDataObserver)
            }
            indicator.setViewPager(this)
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            toolbar.title = state.toolbarTitle
            indicator.isVisible = state.indicatorVisible
            progressBar.isVisible = state.progressBarVisible

            author.text = state.authorText
            summaryContent.text = state.summaryContentText
            extraContent.text = state.extraContentText
            writtenTime.text = state.writtenTime

            mediaDetailAdapter.replaceData(state.mediaDetailItem.children)
        }
    }
}