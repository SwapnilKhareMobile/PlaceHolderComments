package com.sw.placeholder.data.repo

import com.sw.placeholder.data.source.CommentsRemoteDataSource
import com.sw.placeholder.model.PlaceHolderResponseItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CommentsRepositoryImplTest {

    private val commentsRemoteDataSource = mockk<CommentsRemoteDataSource>(relaxed = true)
    private val commentsRepository = CommentsRepositoryImpl(commentsRemoteDataSource)

    @Test
    fun `getComments emits list of items on success`() = runBlocking {
        val expectedResponse = listOf(
            PlaceHolderResponseItem(id = 1, name = "name1", body = "body1", email = "email1", postId = 1),
            PlaceHolderResponseItem(id = 2, name = "name2", body = "body2", email = "email2", postId = 2)
        )

        coEvery { commentsRemoteDataSource.getComments() } returns expectedResponse

        val result = commentsRepository.getComments().toList()

        assertEquals(listOf(expectedResponse), result) //
        coVerify{ commentsRemoteDataSource.getComments() }
    }

    @Test
    fun `getComments emits empty list on error`() = runBlocking {
        coEvery { commentsRemoteDataSource.getComments() } returns emptyList()

        val result = commentsRepository.getComments().toList()

        assertEquals(listOf(emptyList<PlaceHolderResponseItem>()), result)
        coVerify { commentsRemoteDataSource.getComments() }
    }
}