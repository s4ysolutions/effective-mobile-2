package com.example.effectivem2.domain.models

import java.io.Serializable

@JvmInline
value class Button (private val dto: com.example.effectivem2.data.dto.Button): Serializable {
    val text: String
        get() = dto.text
}