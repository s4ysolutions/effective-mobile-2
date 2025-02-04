package com.example.effectivem2.data.retrofit.beans.conversions

import java.text.SimpleDateFormat
import java.util.Date

private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

internal fun String.toDate(): Date? =
    try {
        dateFormat.parse(this)
    } catch (e: Exception) {
        null
    }