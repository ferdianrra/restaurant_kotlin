package com.ferdi.restaurankotlin.utils

object TestHooks {
    var refreshTrigger: (() -> Unit)? = null
}