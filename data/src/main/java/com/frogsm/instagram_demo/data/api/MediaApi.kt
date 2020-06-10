package com.frogsm.instagram_demo.data.api

import com.frogsm.instagram_demo.data.media.data.MediaCollectionData
import com.frogsm.instagram_demo.data.media.data.MediaData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaApi {

    companion object {
        private const val MEDIA_FIELDS =
            "id, username, caption, media_type, media_url, thumbnail_url, timestamp"
    }

    @GET("/{id}")
    suspend fun getMedia(
        @Path("id") id: String,
        @Query("fields") fields: String = MEDIA_FIELDS
    ): MediaData

    @GET("/me/media")
    suspend fun getMediaCollection(
        @Query("fields") fields: String = MEDIA_FIELDS
    ): MediaCollectionData
}