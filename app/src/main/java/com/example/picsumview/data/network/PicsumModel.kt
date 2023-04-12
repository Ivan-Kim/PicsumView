package com.example.picsumview.data.network

import com.google.gson.annotations.SerializedName

data class PicsumModel(
    @SerializedName("download_url")
    val link: String,
    @SerializedName("author")
    val author: String,
)
