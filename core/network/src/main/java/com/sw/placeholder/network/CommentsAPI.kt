package com.sw.placeholder.network

import com.sw.placeholder.common.COMMENTS_API
import com.sw.placeholder.model.PlaceHolderResponse
import retrofit2.Response
import retrofit2.http.GET

interface CommentsAPI {

    @GET(COMMENTS_API)
    suspend fun getCommentsAPI(): Response<PlaceHolderResponse>
}