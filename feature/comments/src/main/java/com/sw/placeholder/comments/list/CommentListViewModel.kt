package com.sw.placeholder.comments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.placeholder.domain.GetCommentsUseCase
import com.sw.placeholder.model.PlaceHolderResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.sw.placeholder.comments.list.ListScreenUIState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CommentListViewModel @Inject constructor(private val getCommentsUseCase: GetCommentsUseCase):ViewModel() {

    val listScreenUiState: StateFlow<ListScreenUIState> = getCommentsUseCase()
        .map<List<PlaceHolderResponseItem>, ListScreenUIState> { items ->
            items.map { model ->
                ListModelUIState(
                    id = model.id,
                    name = model.name,
                    body = model.body
                )
            }.let(::Success)

    }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ListScreenUIState.Loading
        )
}