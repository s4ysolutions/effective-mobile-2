package com.example.effectivem2.data.retrofit

import com.example.effectivem2.data.CoordinatesProvider
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future


class RetrofitCoordinatesProvider : CoordinatesProvider {
    override fun fetchCoordinates(address: String): Future<Pair<Double, Double>> {
        val future = CompletableFuture<Pair<Double, Double>>()

        val client = OkHttpClient()
        val url = "https://nominatim.openstreetmap.org/search?q=${address}&format=json"

        val request = Request.Builder()
            .url(url)
            .addHeader("User-Agent", "EffectiveM2/1.0") // Required by Nominatim
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                println("Request failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string()
                    val jsonArray = JSONArray(responseBody)

                    if (jsonArray.length() > 0) {
                        val firstResult = jsonArray.getJSONObject(0)
                        val latitude = firstResult.getString("lat").toDouble()
                        val longitude = firstResult.getString("lon").toDouble()
                        future.complete(Pair(longitude, latitude))
                    }
                } else {
                    future.completeExceptionally(Error("Request failed with code: ${response.code()}"))
                }
            }
        })
        return future
    }
}