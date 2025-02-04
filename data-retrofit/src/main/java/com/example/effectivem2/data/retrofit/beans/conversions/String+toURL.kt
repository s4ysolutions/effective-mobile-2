package com.example.effectivem2.data.retrofit.beans.conversions

import java.net.URL

internal fun String.toURL(): URL? =
    try {
        URL(this)
    } catch (e: Exception) {
        null
    }