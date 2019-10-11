package com.siddhi.lowesgig.utils.common

import java.util.concurrent.atomic.AtomicBoolean

data class Event<out T>(private val content: T) {

    private var hasBeenHandled = AtomicBoolean(false)

    /**
     * Returns the content and prevents its use again.
     */
    fun getIfNotHandled(): T? = if (hasBeenHandled.getAndSet(true)) null else content

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peek(): T = content
}