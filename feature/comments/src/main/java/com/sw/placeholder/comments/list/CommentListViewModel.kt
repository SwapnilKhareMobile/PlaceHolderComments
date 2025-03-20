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
import com.sw.placeholder.comments.shared.GlobalValues
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CommentListViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val globalValues: GlobalValues
) :
    ViewModel() {

    val listScreenUiState: StateFlow<ListScreenUIState> = getCommentsUseCase()
        .map<List<PlaceHolderResponseItem>, ListScreenUIState> { items ->
            items.sortedBy { model -> model.name }.map { model ->
                ListModelUIState(
                    id = model.id,
                    name = model.name,
                    body = model.body,
                    email = model.email
                )
            }.let(::Success)

        }.catch { throwable ->
            emit(ListScreenUIState.Error(throwable.message ?: "Unknown error"))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ListScreenUIState.Loading
        )

    fun setSharedData(value: PlaceHolderResponseItem){
        globalValues.setSharedData(value)
    }
}