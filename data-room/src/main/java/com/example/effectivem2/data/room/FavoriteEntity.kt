package com.example.effectivem2.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favs")
data class FavoriteEntity(
    @PrimaryKey val favId: String
)
