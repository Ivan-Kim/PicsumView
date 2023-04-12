package com.example.picsumview.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/list")
    suspend fun fetchPictures(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): List<PicsumModel>

}
