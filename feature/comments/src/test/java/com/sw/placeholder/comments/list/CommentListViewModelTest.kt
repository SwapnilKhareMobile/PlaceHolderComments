package com.sw.placeholder.comments.list

import app.cash.turbine.test
import com.sw.placeholder.comments.shared.GlobalValues
import com.sw.placeholder.domain.GetCommentsUseCase
import com.sw.placeholder.model.PlaceHolderResponseItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import java.io.IOException
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommentListViewModelTest{

    private val mainDispatcherRule = StandardTestDispatcher()

    private lateinit var viewModel: CommentListViewModel

    private val getCommentsUseCase: GetCommentsUseCase = mockk()
    private val globalValues: GlobalValues = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainDispatcherRule)
        every { getCommentsUseCase() } returns flowOf(sampleComments)

        viewModel = CommentListViewModel(getCommentsUseCase, globalValues)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `ViewModel emits Success state when comments are loaded successfully`() = runTest {
        val viewModel = CommentListViewModel(getCommentsUseCase, globalValues)
        viewModel.listScreenUiState.test {
            assertEquals(ListScreenUIState.None, awaitItem())
            assertEquals(ListScreenUIState.Loading, awaitItem())
            assertTrue(awaitItem() is ListScreenUIState.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `ViewModel emits Error state when fetching comments fails`() = runTest {
        every { getCommentsUseCase() } returns flow { throw IOException("Network error") }

        viewModel = CommentListViewModel(getCommentsUseCase, globalValues)

        viewModel.listScreenUiState.test {
            assertEquals(ListScreenUIState.None, awaitItem())
            assertEquals(ListScreenUIState.Loading, awaitItem())
            val errorState = awaitItem() as ListScreenUIState.Error
            assertEquals("Network error", errorState.message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `ViewModel retries and fetches data again`() = runTest {
        every { getCommentsUseCase() } returns flowOf(emptyList())

        viewModel = CommentListViewModel(getCommentsUseCase, globalValues)

        viewModel.retry()

        viewModel.listScreenUiState.test {
            assertEquals(ListScreenUIState.Loading, awaitItem())
            assertTrue(awaitItem() is ListScreenUIState.Success)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `ViewModel updates shared data`() {
        val item = PlaceHolderResponseItem("Body", "email@test.com", 1, "test", 1)

        viewModel.setSharedData(item)

        verify { globalValues.setSharedData(item) }
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