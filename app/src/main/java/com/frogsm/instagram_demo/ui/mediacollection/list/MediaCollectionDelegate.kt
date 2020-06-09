package com.frogsm.instagram_demo.ui.mediacollection.list

import javax.inject.Inject

interface MediaCollectionDelegate
    : ImageMediaCollectionDelegate, AlbumMediaCollectionDelegate

class MediaCollectionDelegateImpl @Inject constructor(
    mediaCollectionImageDelegateImpl: ImageMediaCollectionDelegateImpl,
    mediaCollectionAlbumDelegateImpl: AlbumMediaCollectionDelegateImpl
) : MediaCollectionDelegate,
    ImageMediaCollectionDelegate by mediaCollectionImageDelegateImpl,
    AlbumMediaCollectionDelegate by mediaCollectionAlbumDelegateImpl