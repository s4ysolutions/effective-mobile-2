package com.example.effectivem2.data.retrofit.beans

import com.example.effectivem2.data.dto.Address

class JsonAddress(private val town: String, private val street: String, private val house: String) {
    val dto: Address
        get() = Address(
            town = town,
            street = street,
            house = house
        )
}