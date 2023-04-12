package com.example.picsumview.ui

import androidx.lifecycle.ViewModel
import com.example.picsumview.data.PicsumModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PicsumViewModel : ViewModel() {

    // TODO: fetch from repository -> API service
    val uiState: Flow<List<PicsumModel>> = flowOf(emptyList())

}
