package com.example.effectivem2.data

import java.util.concurrent.Future

interface CoordinatesProvider {
    fun fetchCoordinates(address: String): Future<Pair<Double, Double>>
}