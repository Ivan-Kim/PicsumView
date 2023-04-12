package com.example.picsumview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.picsumview.data.network.PicsumModel
import com.example.picsumview.data.network.PicsumPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PicsumViewModel @Inject constructor(
    private val picsumPagingSource: PicsumPagingSource
) : ViewModel() {

    val uiState: Flow<PagingData<PicsumModel>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { picsumPagingSource }
        ).flow.cachedIn(viewModelScope)

}
