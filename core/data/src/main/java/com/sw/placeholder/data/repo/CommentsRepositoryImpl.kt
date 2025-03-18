package com.sw.placeholder.data.repo

import com.sw.placeholder.data.source.CommentsRemoteDataSource
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(private val commentsRemoteDataSource: CommentsRemoteDataSource):CommentsRepository {
}