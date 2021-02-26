package com.frogsm.instagram_demo.ui.mediacollection

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.extensions.navigateSafely
import com.frogsm.instagram_demo.extensions.showLongSnackBar
import com.frogsm.instagram_demo.extensions.toDp
import com.frogsm.instagram_demo.ui.MainActivityViewModel
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionAdapter
import com.frogsm.instagram_demo.util.bindPageableScroll
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_collection.*
import kotlinx.android.synthetic.main.layout_user_card.*
import javax.inject.Inject

@AndroidEntryPoint
class MediaCollectionFragment : Fragment(R.layout.fragment_media_collection) {

    val viewModel by viewModels<MediaCollectionViewModel>()
    private val args by navArgs<MediaCollectionFragmentArgs>()

    private val activityViewModel by activityViewModels<MainActivityViewModel>()

    @Inject
    lateinit var mediaCollectionAdapter: MediaCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start(args.userName)
    }

    private fun initUi() {
        coordinatorLayout.requestApplyInsets()
        collectionList.apply {
            adapter = mediaCollectionAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            itemAnimator = null

            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val space = 10.toDp
                    val position = parent.getChildAdapterPosition(view)
                    when (position % 3) {
                        0 -> outRect.set(0, space, space, space)
                        1 -> outRect.set(space, space, space, space)
                        2 -> outRect.set(space, space, 0, space)
                    }
                }
            })

            bindPageableScroll(this@MediaCollectionFragment, viewModel)
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            toolbar.title = state.toolbarTitle
            progressBar.isVisible = state.progressBarVisible

            mediaCollectionAdapter.replaceData(state.mediaCollectionItems)

            state.userItem?.let {
                userId.text = it.id
                mediaCount.text = it.mediaCount
            }

            state.showSnackBar?.observeOnlyOnce {
                showLongSnackBar(it)
            }

            state.navigateMediaDetail?.observeOnlyOnce { (userName, mediaId) ->
                val action = MediaCollectionFragmentDirections
                    .actionMediaCollectionFragmentToMediaDetailFragment(
                        userName = userName,
                        mediaId = mediaId
                    )
                findNavController().navigateSafely(action)
            }

            state.commonErrorHandle?.observeOnlyOnce {
                activityViewModel.onCommonErrorHandle(it)
            }
        }
    }
}