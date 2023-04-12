package com.example.picsumview.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultPicsumRepository @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher,
) : PicsumRepository {

    override suspend fun getPictures(): Flow<List<PicsumModel>> = flow {
        val response = apiService.fetchPictures()
        emit(response)
    }.catch { e ->
        e.printStackTrace()
    }.flowOn(ioDispatcher)

}
