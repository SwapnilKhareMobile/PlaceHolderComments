import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sw.placeholder.comments.list.CommentListViewModel
import com.sw.placeholder.comments.list.ListModelUIState
import com.sw.placeholder.comments.list.ListScreenUIState
import com.sw.placeholder.common.AppError
import com.sw.placeholder.common.AppLoading
import com.sw.placeholder.common.MyAppTopBar
import com.sw.placeholder.model.PlaceHolderResponseItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommentListScreen(
    modifier: Modifier = Modifier,
    onClick: (PlaceHolderResponseItem) -> Unit,
    viewModel: CommentListViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        MyAppTopBar(
            title = "List",
            onClick = {},
            isBackNavigation = false
        )
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            val uiState = viewModel.listScreenUiState.collectAsStateWithLifecycle()

            when (uiState.value) {
                is ListScreenUIState.Error -> AppError(modifier, {viewModel.retry()})
                ListScreenUIState.Loading -> AppLoading(modifier)
                is ListScreenUIState.Success -> {
                    val list = (uiState.value as ListScreenUIState.Success).comments
                    LazyColumn {
                        items(list) {
                            CommentsListItem(modifier = modifier, list, onClick)
                        }
                    }

                }

                ListScreenUIState.None -> {}
            }
        }
    }
}

@Composable
fun CommentsListItem(
    modifier: Modifier,
    list: List<ListModelUIState>,
    onClick: (PlaceHolderResponseItem) -> Unit
) {
    for (item in list) {
        Card(
            modifier = modifier.fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onClick(
                        PlaceHolderResponseItem(
                            body = item.body,
                            email = item.email,
                            id = item.id,
                            name = item.name,
                            postId = 0
                        )
                    )
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                Text(text = item.name, fontSize = 24.sp)
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = item.body)
            }

        }
    }
}