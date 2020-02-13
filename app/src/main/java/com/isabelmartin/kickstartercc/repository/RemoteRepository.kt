package com.isabelmartin.kickstartercc.repository

import com.google.gson.JsonObject
import com.isabelmartin.kickstartercc.models.GifModel
import com.isabelmartin.kickstartercc.viewmodel.SearchGifViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository {

    // TODO: provide this via injection if you have time
    fun provideGiphyAPI() = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GiphyAPI.BASE_URL)
            .build()
            .create(GiphyAPI::class.java)

    fun search(query: String) {
        provideGiphyAPI().searchGifs(
            q = query
        ).enqueue(object : Callback<List<GifModel>> {
            override fun onFailure(call: Call<List<GifModel>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<GifModel>>,
                response: Response<List<GifModel>>
            ) {
            }
        })
    }
}
