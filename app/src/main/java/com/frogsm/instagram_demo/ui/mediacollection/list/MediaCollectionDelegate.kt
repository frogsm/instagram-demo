package com.frogsm.instagram_demo.ui.mediacollection.list

import javax.inject.Inject

interface MediaCollectionDelegate
    : ImageMediaCollectionDelegate, VideoMediaCollectionDelegate, AlbumMediaCollectionDelegate

class MediaCollectionDelegateImpl @Inject constructor(
    imageMediaCollectionDelegateImpl: ImageMediaCollectionDelegateImpl,
    videoMediaCollectionDelegateImpl: VideoMediaCollectionDelegateImpl,
    albumMediaCollectionDelegateImpl: AlbumMediaCollectionDelegateImpl
) : MediaCollectionDelegate,
    ImageMediaCollectionDelegate by imageMediaCollectionDelegateImpl,
    VideoMediaCollectionDelegate by videoMediaCollectionDelegateImpl,
    AlbumMediaCollectionDelegate by albumMediaCollectionDelegateImpl