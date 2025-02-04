package com.example.effectivem2.domain.models

import java.io.Serializable
import java.net.URL


@JvmInline
value class Offer(private val dto: com.example.effectivem2.data.dto.Offer): Serializable {
    val type: OfferType
        get() = OfferType.valueOf(dto.type.name)
    val title: String
        get() = dto.title
    val link: URL?
        get() = dto.link
    val buttonText: String?
        get() = dto.buttonText
}