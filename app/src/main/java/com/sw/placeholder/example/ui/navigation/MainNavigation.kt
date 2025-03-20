package com.sw.placeholder.example.ui.navigation

import CommentListScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sw.placeholder.comments.detail.CommentDetailScreen
import com.sw.placeholder.comments.list.CommentListViewModel

@Composable
fun MainNavigation(navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = "comments_list") {
        composable(route = "comments_list") {
            val viewModel = hiltViewModel<CommentListViewModel>()
            CommentListScreen(
                onClick = { data ->
                    viewModel.setSharedData(data)
                    navHostController.navigate("comments_detail")
                }
            )
        }
        composable("comments_detail") {
            CommentDetailScreen(
                onClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }

}


