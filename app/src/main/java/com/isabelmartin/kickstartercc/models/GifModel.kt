package com.isabelmartin.kickstartercc.models

import com.google.gson.annotations.SerializedName

/**
 * DTO Object for the Gif response from Giphy API
 */
class GifModel {
    @SerializedName("type") var type: String? = ""
    @SerializedName("id") var id: String? = ""
    @SerializedName("type") var slug: String? = ""
    @SerializedName("type") var url: String? = ""
    @SerializedName("type") var bitly_url: String? = ""
    @SerializedName("type") var embed_url: String? = ""
}