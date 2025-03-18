package com.sw.placeholder.data.source

import com.sw.placeholder.model.PlaceHolderResponseItem
import com.sw.placeholder.network.CommentsAPI
import javax.inject.Inject

class CommentsRemoteDataSourceImpl @Inject constructor(private val commentsAPI: CommentsAPI):CommentsRemoteDataSource {
    override suspend fun getComments(): List<PlaceHolderResponseItem> {
        return commentsAPI.getCommentsAPI().body()?: emptyList()
    }
}