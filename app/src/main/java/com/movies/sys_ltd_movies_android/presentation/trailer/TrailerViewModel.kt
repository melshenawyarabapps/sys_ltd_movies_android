package com.movies.sys_ltd_movies_android.presentation.trailer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.sys_ltd_movies_android.R
import com.movies.sys_ltd_movies_android.domain.usecase.GetMovieTrailerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel @Inject constructor(
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<TrailerUiState>(TrailerUiState.Loading)
    val uiState: StateFlow<TrailerUiState> = _uiState.asStateFlow()

    private var currentMovieId: Int = -1

    init {
        currentMovieId = savedStateHandle.get<Int>("movieId") ?: -1
        if (currentMovieId != -1) {
            fetchTrailer(currentMovieId)
        } else {
            _uiState.value = TrailerUiState.Error(messageResId = R.string.invalid_movie_id)
        }
    }

    fun retry() {
        if (currentMovieId != -1) {
            fetchTrailer(currentMovieId)
        }
    }

    private fun fetchTrailer(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = TrailerUiState.Loading
            try {
                val trailer = getMovieTrailerUseCase(movieId)
                if (trailer != null) {
                    _uiState.value = TrailerUiState.Success(trailer)
                } else {
                    _uiState.value = TrailerUiState.NoVideos
                }
            } catch (e: Exception) {
                _uiState.value = TrailerUiState.Error(
                    message = e.message,
                    messageResId = R.string.error_occurred
                )
            }
        }
    }
}
