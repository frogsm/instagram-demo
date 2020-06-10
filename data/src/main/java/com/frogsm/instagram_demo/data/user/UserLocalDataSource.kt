package com.frogsm.instagram_demo.data.user

import com.frogsm.instagram_demo.data.user.data.UserData
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(

) {
    var user: UserData? = null
}