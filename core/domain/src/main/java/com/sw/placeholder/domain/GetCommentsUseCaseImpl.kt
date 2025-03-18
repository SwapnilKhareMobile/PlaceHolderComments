package com.sw.placeholder.domain

import com.sw.placeholder.data.repo.CommentsRepository
import javax.inject.Inject

class GetCommentsUseCaseImpl @Inject constructor(private val commentsRepository: CommentsRepository):GetCommentsUseCase {

}