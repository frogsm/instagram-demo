package com.frogsm.instagram_demo.ui.mapper

import com.frogsm.instagram_demo.domain.entity.User
import com.frogsm.instagram_demo.ui.mediacollection.user.UserItem

fun User.mapToUserItem(): UserItem {
    return UserItem(id, mediaCount.toString())
}