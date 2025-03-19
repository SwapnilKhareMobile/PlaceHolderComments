import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun CommentListScreen(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    viewModel: CommentListViewModel = hiltViewModel()
) {
    val uiState = viewModel.listScreenUiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        is ListScreenUIState.Error -> {}
        ListScreenUIState.Loading -> {}
        is ListScreenUIState.Success -> {
            val list = (uiState.value as ListScreenUIState.Success).comments
            LazyColumn {
                items(list) {
                    CommentsListItem(modifier = modifier, list, onClick)
                }
            }

        }
    }
}

@Composable
fun CommentsListItem(modifier: Modifier, list: List<ListModelUIState>, onClick: (Int) -> Unit) {
    for (item in list) {
        Card(
            modifier = modifier.padding(16.dp).clickable { onClick(item.id) },
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Title: $item.name", fontSize = 24.sp)
                Text(text = item.body)
            }

        }
    }
}