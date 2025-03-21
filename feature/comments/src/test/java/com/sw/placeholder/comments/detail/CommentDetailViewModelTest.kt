package com.sw.placeholder.comments.detail

import app.cash.turbine.test
import com.sw.placeholder.comments.shared.GlobalValues
import com.sw.placeholder.model.PlaceHolderResponseItem
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommentDetailViewModelTest{
    private val mainDispatcherRule = StandardTestDispatcher()

    private lateinit var viewModel: CommentDetailViewModel

    private val globalValues: GlobalValues = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainDispatcherRule)
        every { globalValues.sharedData } returns MutableStateFlow(sampleComment)

        viewModel = CommentDetailViewModel(globalValues)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `ViewModel emits expected shared data`() = runTest {
        viewModel.detailsUIState.test {
            assertEquals(sampleComment, awaitItem()) // Ensure ViewModel emits correct data
            cancelAndIgnoreRemainingEvents()
        }
    }

    companion object {
        private val sampleComment = PlaceHolderResponseItem(
            id = 1,
            name = "John Doe",
            body = "This is a comment",
            email = "john.doe@example.com",
            postId = 1
        )
    }
}