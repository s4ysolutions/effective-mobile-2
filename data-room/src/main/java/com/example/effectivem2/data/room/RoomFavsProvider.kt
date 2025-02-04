package com.example.effectivem2.data.room

import android.content.Context
import java.util.Base64
import com.example.effectivem2.data.FavsProvider
import com.example.effectivem2.data.dto.Id
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RoomFavsProvider(context: Context): FavsProvider {
    private val dao: FavoritesDao = AppDatabase.getDatabase(context).favoritesDao()

    override suspend fun addFav(id: Id) {
        dao.addToFav(FavoriteEntity(serializeId(id)))
    }

    override suspend fun removeFav(id: Id) {
        dao.removeFromFav(FavoriteEntity(serializeId(id)))
    }

    override suspend fun isFav(id: Id): Boolean {
        return dao.isFav(serializeId(id))
    }

    companion object {
        private fun serializeId(id: Id): String {
            val byteStream = ByteArrayOutputStream()
            ObjectOutputStream(byteStream).use { it.writeObject(id) }
            return Base64.getEncoder().encodeToString(byteStream.toByteArray())
        }

        private fun deserializeId(serializedId: String): Id {
            val byteData = Base64.getDecoder().decode(serializedId)
            val byteStream = ByteArrayInputStream(byteData)
            return ObjectInputStream(byteStream).use { it.readObject() as Id }
        }
    }
}