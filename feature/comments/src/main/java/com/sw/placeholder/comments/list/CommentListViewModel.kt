package com.sw.placeholder.comments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.placeholder.comments.list.ListScreenUIState.Success
import com.sw.placeholder.comments.shared.GlobalValues
import com.sw.placeholder.domain.GetCommentsUseCase
import com.sw.placeholder.model.PlaceHolderResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CommentListViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val globalValues: GlobalValues
) :
    ViewModel() {
    private val _listScreenUiState = MutableStateFlow<ListScreenUIState>(ListScreenUIState.None)
    val listScreenUiState: StateFlow<ListScreenUIState> = _listScreenUiState

    init {
        getCommentsData()
    }

    private fun getCommentsData() {
        getCommentsUseCase()
            .map<List<PlaceHolderResponseItem>, ListScreenUIState> { items ->
                items.sortedBy { model -> model.name }.map { model ->
                    ListModelUIState(
                        id = model.id,
                        name = model.name,
                        body = model.body,
                        email = model.email
                    )
                }.let(::Success)
            }
            .onStart { _listScreenUiState.value = ListScreenUIState.Loading }
            .catch { throwable ->
                emit(ListScreenUIState.Error(throwable.message ?: "Unknown error"))
            }
            .onEach { state -> _listScreenUiState.value = state }
            .launchIn(viewModelScope)
    }

    fun setSharedData(value: PlaceHolderResponseItem) {
        globalValues.setSharedData(value)
    }

    fun retry() {
        _listScreenUiState.value = ListScreenUIState.Loading
        getCommentsData()
    }
}