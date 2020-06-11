package com.frogsm.instagram_demo.data.media

import com.frogsm.instagram_demo.data.media.data.MediaData
import com.frogsm.instagram_demo.data.media.data.MediaDataRM
import com.frogsm.instagram_demo.data.media.data.MediaTypeData
import com.frogsm.instagram_demo.data.realm.MyRealm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import javax.inject.Inject

class MediaLocalDataSource @Inject constructor(
    private val myRealm: MyRealm
) {

    fun getMedia(id: String): MediaData? {
        var mediaData: MediaData? = null

        myRealm.realm.use { realm ->
            realm.executeTransaction { innerRealm ->
                val mediaDataRM = innerRealm.where<MediaDataRM>()
                    .equalTo(MediaDataRM::id.name, id)
                    .findFirst()
                    ?: innerRealm.createObject(id)

                mediaData = MediaData(
                    mediaDataRM.id,
                    MediaTypeData.MoshiAdapter().fromJson(mediaDataRM.mediaType),
                    mediaDataRM.mediaUrl,
                    null,
                    null,
                    null,
                    null
                )
            }
        }

        return mediaData
    }

    fun addMedias(list: List<MediaData>) {
        list.forEach { data ->
            myRealm.realm.use { realm ->
                realm.executeTransaction { innerRealm ->
                    val mediaDataRM = innerRealm.where<MediaDataRM>()
                        .equalTo(MediaDataRM::id.name, data.id)
                        .findFirst()
                        ?: innerRealm.createObject(data.id)

                    mediaDataRM.mediaType = data.media_type.type
                    mediaDataRM.mediaUrl = data.media_url

                    innerRealm.insertOrUpdate(mediaDataRM)
                }
            }
        }
    }
}