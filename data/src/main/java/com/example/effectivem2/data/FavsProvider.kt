package com.example.effectivem2.data

import com.example.effectivem2.data.dto.Id

interface FavsProvider {
    suspend fun addFav(id: Id)
    suspend fun removeFav(id: Id)
    suspend fun isFav(id: Id): Boolean
}