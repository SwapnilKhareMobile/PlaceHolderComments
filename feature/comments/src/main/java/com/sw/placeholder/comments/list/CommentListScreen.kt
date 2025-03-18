import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sw.placeholder.comments.list.CommentListViewModel
import com.sw.placeholder.comments.list.ListScreenUIState

@Composable
fun CommentListScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit, viewModel: CommentListViewModel = hiltViewModel()){
    val uiState = viewModel.listScreenUiState.collectAsStateWithLifecycle()
}