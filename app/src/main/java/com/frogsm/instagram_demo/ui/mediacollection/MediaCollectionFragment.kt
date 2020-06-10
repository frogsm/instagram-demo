package com.frogsm.instagram_demo.ui.mediacollection

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import com.frogsm.instagram_demo.ui.mediacollection.list.MediaCollectionAdapter
import kotlinx.android.synthetic.main.fragment_media_collection.*
import kotlinx.android.synthetic.main.layout_user_card.*
import javax.inject.Inject

class MediaCollectionFragment : BaseFragment(R.layout.fragment_media_collection) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModels<MediaCollectionViewModel> { viewModelFactory }
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
        }
    }
}