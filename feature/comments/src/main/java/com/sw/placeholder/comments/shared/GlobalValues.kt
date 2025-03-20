package com.sw.placeholder.comments.shared

import com.sw.placeholder.model.PlaceHolderResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalValues @Inject constructor() {

    private val _sharedData = MutableStateFlow<PlaceHolderResponseItem?>(null)
    val sharedData: StateFlow<PlaceHolderResponseItem?> = _sharedData.asStateFlow()

    fun setSharedData(value: PlaceHolderResponseItem) {
        _sharedData.value = value
    }
}