package com.sw.placeholder.domain

import com.sw.placeholder.data.repo.CommentsRepository
import com.sw.placeholder.model.PlaceHolderResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCommentsUseCaseImpl @Inject constructor(private val commentsRepository: CommentsRepository) :
    GetCommentsUseCase {

    private val allItems: Flow<List<PlaceHolderResponseItem>> =
        commentsRepository.getComments().flowOn(Dispatchers.IO)

    override operator fun invoke(): Flow<List<PlaceHolderResponseItem>> {
        return allItems
    }

    override suspend fun observeCommentsById(id: Int): Flow<PlaceHolderResponseItem> {
        return allItems.map { items ->
            items.firstOrNull { model -> model.id == id }
                ?: throw NoSuchElementException("$id not found")
        }
    }
}