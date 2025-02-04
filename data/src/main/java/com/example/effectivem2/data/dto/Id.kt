package com.example.effectivem2.data.dto

import java.io.Serializable
import java.util.UUID

sealed interface Id: Serializable

@JvmInline
value class UuidId(val uuid: UUID): Id, Serializable {
    constructor(id: String): this(UUID.fromString(id))
}

@JvmInline
value class StringId(val string: String): Id, Serializable
