package com.sw.placeholder.domain

import com.sw.placeholder.model.PlaceHolderResponseItem
import kotlinx.coroutines.flow.Flow

interface GetCommentsUseCase {

    operator fun invoke(): Flow<List<PlaceHolderResponseItem>>
    suspend fun observeCommentsById(id: Int): Flow<PlaceHolderResponseItem>
}