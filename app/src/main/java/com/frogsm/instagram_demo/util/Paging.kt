package com.frogsm.instagram_demo.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.bindPageableScroll(fragment: Fragment, pageable: Pageable) {
    val lifecycle = fragment.viewLifecycleOwner.lifecycle
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            (recyclerView.layoutManager as? GridLayoutManager)?.apply {
                val lastVisiblePos = findLastVisibleItemPosition()

                if (itemCount in 0..lastVisiblePos + 3) {
                    pageable.onNextPageRequest()
                }
            }
        }
    }

    val lifecycleEventObserver = object : LifecycleEventObserver {
        init {
            addOnScrollListener(scrollListener) // 스크롤 리스너 등록
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                source.lifecycle.removeObserver(this)
                removeOnScrollListener(scrollListener) // 스크롤 리스너 해제
            }
        }
    }

    if (lifecycle.currentState != Lifecycle.State.DESTROYED) {
        lifecycle.addObserver(lifecycleEventObserver)
    }
}

interface Pageable {
    fun onNextPageRequest()

    val loadSuccessPageUrlSet: HashSet<String>
}