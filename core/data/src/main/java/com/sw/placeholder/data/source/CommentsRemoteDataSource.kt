package com.sw.placeholder.data.source

import com.sw.placeholder.model.PlaceHolderResponseItem

interface CommentsRemoteDataSource {

    suspend fun getComments(): List<PlaceHolderResponseItem>
}