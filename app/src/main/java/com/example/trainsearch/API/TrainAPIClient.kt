package com.example.trainsearch.API

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException


class TrainApiClient private constructor() {

    private val client: OkHttpClient = OkHttpClient()

    @Throws(IOException::class)
    fun search(searchQuery: String): String {
        val mediaType = "application/json".toMediaTypeOrNull()

        val requestBody = RequestBody.create(mediaType, "{\"search\": \"$searchQuery\"}")

        val request = Request.Builder()
            .url("https://trains.p.rapidapi.com/")
            .post(requestBody)
            .addHeader("content-type", "application/json")
            .addHeader("X-RapidAPI-Key", "b95904c45amsh9d47603ef5c22b5p1fde0fjsn1c42cc049f34")
            .addHeader("X-RapidAPI-Host", "trains.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        return response.body?.string() ?: throw IOException("Failed to get response body")
    }

    companion object {
        @Volatile private var instance: TrainApiClient? = null

        fun getInstance(): TrainApiClient {
            return instance ?: synchronized(this) {
                instance ?: TrainApiClient().also { instance = it }
            }
        }
    }
}