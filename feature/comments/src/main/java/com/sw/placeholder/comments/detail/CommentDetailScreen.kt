package com.sw.placeholder.comments.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sw.placeholder.common.MyAppTopBar
import com.sw.placeholder.model.PlaceHolderResponseItem

@Composable
fun CommentDetailScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    viewModel: CommentDetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        MyAppTopBar(
            title = "Detail",
            onClick = onClick,
            isBackNavigation = true
        )
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            val detailUIState = viewModel.detailsUIState.collectAsStateWithLifecycle()
            detailUIState.value?.let { CommentsDetailCard(modifier, it) }
        }
    }
}

@Composable
fun CommentsDetailCard(modifier: Modifier, value: PlaceHolderResponseItem) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = value.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = value.body)
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = modifier.weight(0.2f),
                    text = "ID: "
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.weight(0.8f),
                    text = value.id.toString(),
                    textAlign = TextAlign.End
                )
            }

            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = modifier.weight(0.2f),
                    text = "Email:"
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.weight(0.8f),
                    text = value.email,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
