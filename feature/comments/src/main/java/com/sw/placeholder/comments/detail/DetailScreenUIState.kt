package com.sw.placeholder.comments.detail

sealed interface DetailScreenUIState {
    data object Loading : DetailScreenUIState
    data class Success(val comments: DetailModelUIState) : DetailScreenUIState

}