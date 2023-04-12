package com.example.picsumview.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class PicsumPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, PicsumModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicsumModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.fetchPictures(page = nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PicsumModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
