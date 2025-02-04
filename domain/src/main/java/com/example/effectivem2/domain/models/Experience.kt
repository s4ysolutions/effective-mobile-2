package com.example.effectivem2.domain.models

import java.io.Serializable

@JvmInline
value class Experience(private val dto: com.example.effectivem2.data.dto.Experience) :
    Serializable {
    val previewText: String
        get() = dto.previewText
    val text: String
        get() = dto.text
}