package com.movies.sys_ltd_movies_android.presentation.trailer

import androidx.annotation.StringRes
import com.movies.sys_ltd_movies_android.domain.model.Video

sealed class TrailerUiState {
    data object Loading : TrailerUiState()
    data class Success(val video: Video) : TrailerUiState()
    data object NoVideos : TrailerUiState()
    data class Error(
        val message: String? = null,
        @StringRes val messageResId: Int? = null
    ) : TrailerUiState() {
        init {
            require(message != null || messageResId != null) {
                "Either message or messageResId must be provided"
            }
        }
    }
}
