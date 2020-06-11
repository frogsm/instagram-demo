package com.frogsm.instagram_demo.util

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun observeOnlyOnce(event: (T) -> Unit) {
        getContentIfNotHandled()?.let(event)
    }

    /**
     * Returns the content and prevents its use again.
     */
    private fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    private fun peekContent(): T = content
}