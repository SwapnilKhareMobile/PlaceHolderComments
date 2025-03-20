package com.sw.placeholder.comments.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CommentDetailScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    viewModel: CommentDetailViewModel = hiltViewModel()
) {
    val detailUIState = viewModel.detailsUIState.collectAsStateWithLifecycle()
    detailUIState.value?.let { Text(text = it.name) }
}