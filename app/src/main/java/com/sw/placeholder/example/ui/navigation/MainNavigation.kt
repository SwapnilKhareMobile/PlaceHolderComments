package com.sw.placeholder.example.ui.navigation

import CommentListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sw.placeholder.comments.detail.CommentDetailScreen

@Composable
fun MainNavigation(navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = "comments_list") {
        composable("comments_list") {
            CommentListScreen(
                onClick = { id ->
                    navHostController.navigate("comments_detail/$id")
                }
            )
        }
        composable("comments_detail/{id}",
            listOf(navArgument("id") { type = NavType.IntType })) {
            CommentDetailScreen(
                onClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }

}


