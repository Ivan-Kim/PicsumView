package com.example.picsumview.data.network

import kotlinx.coroutines.flow.Flow

interface PicsumRepository {

    suspend fun getPictures(): Flow<List<PicsumModel>>

}
