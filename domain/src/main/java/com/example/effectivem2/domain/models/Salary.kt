package com.example.effectivem2.domain.models

import java.io.Serializable

@JvmInline
value class Salary(private val dto: com.example.effectivem2.data.dto.Salary): Serializable {
    val short: String?
        get() = dto.short
    val full: String?
        get() = dto.full
}