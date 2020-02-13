package com.isabelmartin.kickstartercc.repository

import com.google.gson.JsonObject
import com.isabelmartin.kickstartercc.models.GifModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyAPI {
    companion object {
        // TODO: obfuscate in production build
        const val KEY = "AFUEb4f6vpFxyhVCayn6gZWTSjtmNieH"
        const val BASE_URL = "https://api.giphy.com"
    }

    @GET("/v1/gifs/search?")
    fun searchGifs(
        @Query("api_key") api_key: String = KEY,
        @Query("q") q: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String? = "",
        @Query("lang") lang: String? = ""
    ):Call<List<GifModel>>
    // TODO: not a god practice use the same DTO as model for your view but we are in a rush, and this is fairly small


}