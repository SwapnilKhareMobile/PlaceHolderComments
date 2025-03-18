package com.sw.placeholder.comments.list

import com.sw.placeholder.model.PlaceHolderResponseItem

sealed interface ListScreenUIState {
    data object Loading : ListScreenUIState
    data class Success(val comments: List<ListModelUIState>) : ListScreenUIState
    data class Error(val message: String) : ListScreenUIState
}