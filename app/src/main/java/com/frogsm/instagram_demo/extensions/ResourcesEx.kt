package com.frogsm.instagram_demo.extensions

import android.content.res.Resources

inline val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

inline val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()