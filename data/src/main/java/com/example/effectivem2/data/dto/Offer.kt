package com.example.effectivem2.data.dto

import com.example.effectivem2.data.dto.OfferType
import java.net.URL


class Offer(
    private val id: Id,
    val type: OfferType,
    val title: String,
    val link: URL?,
    val buttonText: String?
)