package com.sw.placeholder.example.ui.navigation

import CommentListScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sw.placeholder.comments.detail.CommentDetailScreen

@Composable
fun MainNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "list",
        modifier = modifier
    ) {
        composable("list"){
            CommentListScreen(modifier, onClick = { id ->
                navController.navigate("detail/$id")
            })
        }
        composable("detail/{id}", listOf(navArgument("id") { type = NavType.StringType })){
            CommentDetailScreen (modifier,navController)
        }

    }
}