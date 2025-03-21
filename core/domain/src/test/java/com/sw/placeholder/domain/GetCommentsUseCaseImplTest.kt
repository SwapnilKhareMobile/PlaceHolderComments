package com.sw.placeholder.domain

import com.sw.placeholder.data.repo.CommentsRepository
import com.sw.placeholder.model.PlaceHolderResponseItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class GetCommentsUseCaseImplTest {
    private val commentsRepository = mockk<CommentsRepository>()
    private lateinit var useCase: GetCommentsUseCaseImpl
    private val testDispatcherRule = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcherRule)
        every { commentsRepository.getComments() } returns flowOf(sampleComments)
        useCase = GetCommentsUseCaseImpl(commentsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke returns list of comments`() = runTest {
        val result = useCase().toList()

        assertEquals(listOf(sampleComments), result)
        verify(exactly = 1) { commentsRepository.getComments() }
    }

    @Test
    fun `observeCommentsById returns correct comment when ID exists`() = runTest {
        val expectedComment = sampleComments[0]

        val result = useCase.observeCommentsById(1).toList()

        assertEquals(listOf(expectedComment), result)
    }

    @Test
    fun `observeCommentsById throws NoSuchElementException when ID not found`() = runTest {
        // When & Then
        val exception = assertFailsWith<NoSuchElementException> {
            useCase.observeCommentsById(999).first()
        }
        assertEquals("999 not found", exception.message)
    }

    companion object {
        private val sampleComments = listOf(
            PlaceHolderResponseItem(
                id = 1,
                name = "John",
                body = "Comment 1",
                email = "john@example.com",
                postId = 1
            ),
            PlaceHolderResponseItem(
                id = 2,
                name = "Jane",
                body = "Comment 2",
                email = "jane@example.com",
                postId = 2
            )
        )
    }
}