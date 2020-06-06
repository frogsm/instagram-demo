package com.frogsm.instagram_demo.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator

fun NavController.navigateSafely(directions: NavDirections, extras: FragmentNavigator.Extras? = null) {
    val action = currentDestination?.getAction(directions.actionId)
    action?.run {
        if(extras != null) {
            navigate(directions, extras)
        } else {
            navigate(directions)
        }
    }
}