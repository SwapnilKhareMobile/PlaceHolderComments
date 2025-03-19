package com.sw.placeholder.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val COMMENTS_API = "comments"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    isBackNavigation: Boolean
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        title = { Text(text = title) },
        navigationIcon = {
            if (isBackNavigation) {
                BackButton(onClick = onClick)
            }
        }
    )
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}



@Composable
fun CommentsDetailCard(modifier: Modifier) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Title", fontSize = 24.sp)
            Text(text = "Body")
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
                    text = "abc",
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
                    text = "Email"
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.weight(0.8f),
                    text = "abc@y",
                    textAlign = TextAlign.End
                )
            }

        }

    }
}
