package com.isabelmartin.kickstartercc.models

// Data needed for displaying one gif on the cell
data class GifDetailModel(
    val actionTitle: String? = "Default title",
    val url: String? = "Default Url",
    val width: Int? = 0,
    val height: Int? = 0
)