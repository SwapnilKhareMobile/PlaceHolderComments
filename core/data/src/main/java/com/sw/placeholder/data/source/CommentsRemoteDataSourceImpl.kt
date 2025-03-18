package com.sw.placeholder.data.source

import com.sw.placeholder.network.CommentsAPI
import javax.inject.Inject

class CommentsRemoteDataSourceImpl @Inject constructor(private val commentsAPI: CommentsAPI):CommentsRemoteDataSource {
}