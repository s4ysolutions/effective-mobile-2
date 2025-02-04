package com.example.effectivem2.data.retrofit.beans

import com.example.effectivem2.data.dto.Experience

class JsonExperience(private val previewText: String, private val text: String) {
    val dto: Experience
        get() =
            Experience(
                previewText = previewText,
                text = text
            )
}