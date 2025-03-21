package com.sw.placeholder.data.source

import com.sw.placeholder.model.PlaceHolderResponseItem
import com.sw.placeholder.network.CommentsAPI
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class CommentsRemoteDataSourceImplTest {

    private val commentsAPI = mockk<CommentsAPI>(relaxed = true)
    private val dataSource = CommentsRemoteDataSourceImpl(commentsAPI)


    @Test
    fun `getComments return list of items on success`() = runBlocking {
        val expected : List<PlaceHolderResponseItem> = listOf(
            PlaceHolderResponseItem(
                id = 1, name = "name1", body = "body1", email = "email1",
                postId = 1
            ),
            PlaceHolderResponseItem(
                id = 2, name = "name2", body = "body2", email = "email2",
                postId = 2
            )
        )
        val response = Response.success(expected)
        coEvery { commentsAPI.getCommentsAPI() } returns response

        val actualResponse = dataSource.getComments()

        assertEquals(expected, actualResponse)
        coVerify{ commentsAPI.getCommentsAPI() }
    }


}