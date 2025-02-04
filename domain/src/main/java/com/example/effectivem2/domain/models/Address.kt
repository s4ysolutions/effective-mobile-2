package com.example.effectivem2.domain.models

import java.io.Serializable

@JvmInline
value class Address(private val dto: com.example.effectivem2.data.dto.Address): Serializable {
    val town: String
        get() = dto.town
    val street: String
        get() = dto.street
    val house: String
        get() = dto.house
}