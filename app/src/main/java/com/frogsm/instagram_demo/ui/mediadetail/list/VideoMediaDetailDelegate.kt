package com.frogsm.instagram_demo.ui.mediadetail.list

import android.net.Uri
import android.view.View
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.extensions.displayThumbnail
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.item_media_detail_video.view.*
import javax.inject.Inject

interface VideoMediaDetailDelegate {
    fun initUi(item: MediaChildrenItem.Video, view: View)
}

class VideoMediaDetailDelegateImpl @Inject constructor(
) : VideoMediaDetailDelegate {

    override fun initUi(item: MediaChildrenItem.Video, view: View) {
        with(view) {
            val dataSourceFactory =
                DefaultDataSourceFactory(context, context.getString(R.string.app_name))

            val mediaSource =
                ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(item.mediaUrl))

            val playerErrorListener = object : Player.EventListener {
                override fun onPlayerError(error: ExoPlaybackException) {
                    when (error.type) {
                        ExoPlaybackException.TYPE_SOURCE -> {
                            // 플레이어로 재생할 수 없는 경우에는 GIF 파일로 판단하여 글라이드로 보여주기
                            imageView.displayThumbnail(item.mediaUrl)
                        }
                    }
                }
            }

            playerView.apply {
                useController = false
                controllerAutoShow = false

                player = SimpleExoPlayer.Builder(context).build().also {
                    it.playWhenReady = true
                    it.repeatMode = Player.REPEAT_MODE_ALL

                    it.prepare(mediaSource)
                    it.addListener(playerErrorListener)
                }
            }
        }
    }
}