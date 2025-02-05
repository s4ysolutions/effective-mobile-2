package com.example.effectivem2.domain

import com.example.effectivem2.data.CoordinatesProvider
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

@Singleton
class GeoService @Inject constructor(private val coordinatesProvider: CoordinatesProvider) {
    suspend fun fetchCoordinates(address: String): Pair<Double, Double> {
        val fv: Future<Pair<Double, Double>> = coordinatesProvider.fetchCoordinates(address)
        // the blocking get() call is moved off to a dedicated thread pool managed
        // by the ForkJoinPool.commonPool()
        // (which CompletableFuture.supplyAsync uses by default).
        return CompletableFuture.supplyAsync{
            @Suppress("BlockingMethodInNonBlockingContext")
            fv.get()
        }.await()
    }
}