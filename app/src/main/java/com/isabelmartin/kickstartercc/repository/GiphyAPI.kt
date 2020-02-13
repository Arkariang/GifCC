package com.isabelmartin.kickstartercc.repository

import com.isabelmartin.kickstartercc.models.ResponseBodyModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyAPI {
    companion object {
        // TODO: improve security if you have time
        const val KEY = "AFUEb4f6vpFxyhVCayn6gZWTSjtmNieH"
        const val BASE_URL = "https://api.giphy.com"
    }

    @GET("/v1/gifs/search?")
    fun searchGifs(
        @Query("api_key") api_key: String = KEY,
        @Query("q") q: String,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String? = "",
        @Query("lang") lang: String? = ""
    ): Single<ResponseBodyModel>
}