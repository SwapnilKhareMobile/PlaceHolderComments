package com.sw.placeholder.comments.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.placeholder.domain.GetCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CommentDetailViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val detailsUIState: StateFlow<DetailScreenUIState> = savedStateHandle
        .getStateFlow<Int?>("id", null)
        .filterNotNull()
        .flatMapLatest { id ->
            getCommentsUseCase.observeCommentsById(id)
        }.map { model ->
            DetailScreenUIState.Success(
                DetailModelUIState(
                    id = model.id,
                    body = model.body,
                    title = model.name,
                    email = model.email,
                )
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailScreenUIState.Loading)

}