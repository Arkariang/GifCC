package com.isabelmartin.kickstartercc.models

import com.google.gson.annotations.SerializedName

/**
 * POJO Object for the Gif response from Giphy API
 */
data class ResponseBodyModel(
    @SerializedName("data")
    var data: List<GiphyModel>
)

data class GiphyModel(
    @SerializedName("type") var type: String? = "",
    @SerializedName("id") var id: String? = "",
    @SerializedName("slug") var slug: String? = "",
    @SerializedName("url") var url: String? = "",
    @SerializedName("bitly_url") var bitly_url: String? = "",
    @SerializedName("embed_url") var embed_url: String? = "",
    @SerializedName("images") var images: Images,
    @SerializedName("title") var title: String,
    @SerializedName("source") var source: String
)

data class Images(
    @SerializedName("fixed_width")
    var smallSize: GifPojo
)

data class GifPojo(
    @SerializedName("url") var gif_url: String? = "",
    @SerializedName("width") var width: String? = "",
    @SerializedName("height") var height: String? = ""
)