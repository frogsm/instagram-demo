package com.frogsm.instagram_demo.ui.mediadetail.list

import javax.inject.Inject

interface MediaDetailDelegate : ImageMediaDetailDelegate, VideoMediaDetailDelegate

class MediaDetailDelegateImpl @Inject constructor(
    private val imageMediaDetailDelegateImpl: ImageMediaDetailDelegateImpl,
    private val videoMediaDetailDelegateImpl: VideoMediaDetailDelegateImpl
) : MediaDetailDelegate,
        ImageMediaDetailDelegate by imageMediaDetailDelegateImpl,
        VideoMediaDetailDelegate by videoMediaDetailDelegateImpl