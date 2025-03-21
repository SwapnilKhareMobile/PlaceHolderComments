package com.sw.placeholder.comments.list

sealed interface ListScreenUIState {
    data object None : ListScreenUIState
    data object Loading : ListScreenUIState
    data class Success(val comments: List<ListModelUIState>) : ListScreenUIState
    data class Error(val message: String) : ListScreenUIState
}