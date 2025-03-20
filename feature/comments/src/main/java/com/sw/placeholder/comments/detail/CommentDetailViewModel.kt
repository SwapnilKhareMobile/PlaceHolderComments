package com.sw.placeholder.comments.detail

import androidx.lifecycle.ViewModel
import com.sw.placeholder.comments.shared.GlobalValues
import com.sw.placeholder.model.PlaceHolderResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CommentDetailViewModel @Inject constructor(
    globalValues: GlobalValues
) : ViewModel() {

    val detailsUIState: StateFlow<PlaceHolderResponseItem?> = globalValues.sharedData
}