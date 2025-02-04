package com.example.effectivem2.data.room

import androidx.room.*

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFav(favorite: FavoriteEntity)

    @Delete
    suspend fun removeFromFav(favorite: FavoriteEntity)

    @Query("SELECT EXISTS(SELECT * FROM favs WHERE favId = :favId)")
    suspend fun isFav(favId: String): Boolean
/*
    @Query("SELECT * FROM favs")
    fun getAllFavs(): Flow<List<FavoriteEntity>>
 */
}
