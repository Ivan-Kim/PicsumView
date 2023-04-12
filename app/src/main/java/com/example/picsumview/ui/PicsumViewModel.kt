package com.example.picsumview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumview.data.network.PicsumModel
import com.example.picsumview.data.network.PicsumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicsumViewModel @Inject constructor(
    private val picsumRepository: PicsumRepository
) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(emptyList<PicsumModel>())

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<List<PicsumModel>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            picsumRepository.getPictures()
                .collect { _uiState.value = it }
        }
    }

}
