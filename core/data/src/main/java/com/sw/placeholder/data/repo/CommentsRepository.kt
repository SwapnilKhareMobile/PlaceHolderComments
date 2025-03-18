package com.sw.placeholder.data.repo

import com.sw.placeholder.model.PlaceHolderResponseItem
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

     fun getComments(): Flow<List<PlaceHolderResponseItem>>

}