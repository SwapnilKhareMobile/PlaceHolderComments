package com.sw.placeholder.data.repo

import com.sw.placeholder.data.source.CommentsRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(private val commentsRemoteDataSource: CommentsRemoteDataSource):CommentsRepository {
    override fun getComments() = flow {
        emit(commentsRemoteDataSource.getComments())
    }
}